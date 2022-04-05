//package richClient.client;
//
//import java.io.IOException;
//import java.net.URL;
//
//import com.google.common.base.Charsets;
//import com.google.common.base.Strings;
//import com.google.common.collect.Lists;
//import com.google.common.io.Resources;
//import com.sun.deploy.ui.AppInfo;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.support.GenericXmlApplicationContext;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.core.io.Resource;
//
///**
// * @author sikou
// * @date 2021/08/05
// */
//public class BaseCacheClient implements ApplicationContextAware, InitializingBean {
//    protected AppInfo appInfo;
//    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
//    private ApplicationContext applicationContext;
//    protected String consumerServiceVersion;
//    private static TairManager tairManager;
//
//    public BaseCacheClient() {
//    }
//
//    // 1.appInfo是调用方对应的应用信息，用于统计和黑白名单控制
//    // 2.根据配置创建对应的tairManager
//    public void afterPropertiesSet() throws Exception {
//        this.logger.warn("{} afterPropertiesSet checking...", this.getClass());
//        if (this.appInfo == null) {
//            this.appInfo = (AppInfo)this.getBeanInstanceFromSpring("appInfo", AppInfo.class);
//        }
//
//        if (this.appInfo == null) {
//            this.appInfo = BeanResourceFactory.appInfo;
//        }
//        if (this.appInfo != null && !Strings.isNullOrEmpty(this.appInfo.getAppName())) {
//            if (null == tairManager) {
//                this.logger.warn("init tair manager for deploy solution={}", BeanResourceFactory.deploySolution);
//                // 根据配置创建不同的tairManager
//                if ("ae".equalsIgnoreCase(BeanResourceFactory.deploySolution)) {
//                    this.logger.warn("init tairManager of honolulu client with ae deploy solution");
//                    tairManager = new MultiClusterTairManager();
//                    ((MultiClusterTairManager)tairManager).setUserName("ae0796f3465e484a");
//                    tairManager.init();
//                } else if ("twz".equalsIgnoreCase(BeanResourceFactory.deploySolution)) {
//                    this.logger.warn("init tairManager of honolulu client with twz deploy solution");
//                    tairManager = new MultiClusterTairManager();
//                    ((MultiClusterTairManager)tairManager).setUserName("20d1ae00a4a04dac");
//                    tairManager.init();
//                } else {
//                    this.logger.warn("init tairManager of honolulu client with group deploy solution");
//                    ProxyMultiTairManager proxyMultiTairManager = new ProxyMultiTairManager();
//                    proxyMultiTairManager.setAreaConstantList(Lists.newArrayList(new String[]{"com.alibaba.honolulu.common.HonoluluTairCacheArea"}));
//                    proxyMultiTairManager.setDefaultExcludeConstant("BASE_NAMESPACE");
//                    proxyMultiTairManager.setTimeout(3000);
//                    proxyMultiTairManager.init();
//                    tairManager = proxyMultiTairManager;
//                }
//            }
//
//            if (null == this.getTairManager()) {
//                this.logger.error("can not found tairManger @see(com.taobao.tair.TairManager)");
//                throw new RuntimeException(this.getClass() + " check failed:tairManager=null");
//            }
//        } else {
//            this.logger.error("Valid Appinfo not found yet, see @(com.alibaba.ju.common.service.AppInfo)");
//            throw new RuntimeException(this.getClass() + " check failed:appInfo=" + this.appInfo);
//        }
//    }
//    // 创建代理类
//    public <T> T createProxyInstanceByClass(Class<T> requiredType) {
//        String beanName = StringUtils.firstLowerCase(requiredType.getSimpleName());
//        return this.createProxyInstanceByClass(beanName, requiredType);
//    }
//    // 1.通过spring的IOC来管理bean
//    // 2.使用JDK动态代理，创建代理类
//    private <T> T createProxyInstanceByClass(String beanName, Class<T> requiredType) {
//        T target = this.getBeanInstanceFromSpring(beanName, requiredType);
//        if (target == null) {
//            // 关键方法，会根据xml配置生成对应的HSFConsumer并注入到IOC容器中,详情见下面HsfConsumer注入
//            target = this.createInstance(beanName, requiredType);
//        }
//
//        if (target == null) {
//            this.logger.error("{} fail to createProxyInstanceByClass, class={}", this.getClass(), requiredType.getName());
//            return null;
//        } else {
//            // 关键方法，真正创建CacheProxy代理
//            return this.createProxyInstance(target);
//        }
//    }
//    // 1. 解析配置文件com/alibaba/honolulu/common/bean/honolulu-hsf-consumer-temp.xml
//    // 2. 生成该接口对应的HSFConsumer xml方式的配置
//    // 3. 加载到IOC容器内
//    public <T> T createInstance(String beanName, Class<T> requiredType) {
//        try {
//            GenericXmlApplicationContext context = new GenericXmlApplicationContext();
//            context.setParent(this.applicationContext);
//            context.load(new Resource[]{BeanResourceFactory.buildResource(beanName, requiredType.getName(), this.consumerServiceVersion)});
//            context.refresh();
//            return context.getBean(beanName);
//        } catch (Exception var4) {
//            this.logger.error("{} createInstance Exception", this.getClass(), var4);
//            return null;
//        }
//    }
//    private <T> T getBeanInstanceFromSpring(String beanName, Class<T> requiredType) {
//        try {
//            return this.applicationContext.getBean(beanName, requiredType);
//        } catch (Exception var4) {
//            this.logger.warn("{} getBeanInstanceFromSpring failed beanName={}, requiredType={}", new Object[]{this.getClass(), beanName, requiredType});
//            return null;
//        }
//    }
//    // 1. 使用JDK动态代理，创建CacheProxy代理
//    private <T> T createProxyInstance(T target) {
//        return (new CacheProxy(this.getTairManager())).bind(target);
//    }
//
//    public TairManager getTairManager() {
//        if (tairManager == null) {
//            tairManager = (TairManager)this.applicationContext.getBean("tairManager", TairManager.class);
//            if (tairManager == null) {
//                this.logger.error("can not found tairManger in Spring ApplicationContext (com.taobao.tair.TairManager)");
//            }
//        }
//
//        return tairManager;
//    }
//
//    public void setTairManager(TairManager tairManager) {
//        BaseCacheClient.tairManager = tairManager;
//    }
//
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }
//
//    protected void handleError(ResultDO result) {
//        if (ExceptionUtil.isBizException(result.getErrorCode())) {
//            throw new LuluBizException(result.getErrorCode(), result.getErrorMessage());
//        } else {
//            throw new LuluSysException(result.getErrorCode(), result.getErrorMessage());
//        }
//    }
//
//    protected void handleException(Throwable t) {
//        throw new LuluSysException(401, "remote hsf service invoke exception. msg=" + ExceptionUtil.getCauseMessage(t), t);
//    }
//
//    public void setAppInfo(AppInfo appInfo) {
//        this.appInfo = appInfo;
//    }
//    // 统一的异常处理
//    public <T> T execute(ServiceClientInvoker<T> invoker) {
//        try {
//            ResultDO<T> result = invoker.invoke();
//            if (result.isSuccess()) {
//                return result.getValue();
//            }
//
//            this.handleError(result);
//        } catch (LuluBizException var3) {
//            throw var3;
//        } catch (LuluSysException var4) {
//            throw var4;
//        } catch (Throwable var5) {
//            this.logger.error("hsf service invoke exception.", var5);
//            this.handleException(var5);
//        }
//
//        return null;
//    }
//
//    public void setConsumerServiceVersion(String consumerServiceVersion) {
//        this.consumerServiceVersion = consumerServiceVersion;
//    }
//
//    public static Resource buildResource(String beanName, String classPath, String consumerServiceVersion) throws
//        IOException {
//        URL resource = Resources.getResource("com/alibaba/honolulu/common/bean/honolulu-hsf-consumer-temp.xml");
//        String contextXML = Resources.toString(resource, Charsets.UTF_8);
//        contextXML = contextXML.replace("${honolulu.dynamic.client.bean.name}", beanName).replace("${honolulu.dynamic.client.interface.class.path}", classPath).replace("${honolulu.service.version}", getHsfVersion(consumerServiceVersion));
//        logger.info("buildResource bean definition=\n" + contextXML);
//        return new ByteArrayResource(contextXML.getBytes());
//    }
//}