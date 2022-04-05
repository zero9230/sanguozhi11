//package richClient.client;
//
///**
// * @author sikou
// * @date 2021/08/05
// */
//// 1. 依赖spring来管理Bean的生命周期
//// 2. BaseCacheClient负责创建代理类和通用逻辑处理
//@Component
//public class PlanClient extends BaseCacheClient implements InitializingBean {
//    private MarketPlanReadService marketPlanReadService;
//    private MarketPlanWriteService marketPlanWriteService;
//    private MarketProductReadService marketProductReadService;
//    private MarketProductWriteService marketProductWriteService;
//
//    // 1. 通过spring来管理Bean的生命周期
//    // 2. 创建JDK动态代理类，来实现先查询tair缓存，未命中再进行Hsf调用
//    public void afterPropertiesSet() throws Exception {
//        this.logger.warn("{} afterPropertiesSet checking......", this.getClass());
//        // 创建tairManger
//        super.afterPropertiesSet();
//        // 创建代理类
//        this.marketPlanReadService = (MarketPlanReadService)this.createProxyInstanceByClass(MarketPlanReadService.class);
//        this.marketPlanWriteService = (MarketPlanWriteService)this.createProxyInstanceByClass(MarketPlanWriteService.class);
//        this.marketProductReadService = (MarketProductReadService)this.createProxyInstanceByClass(MarketProductReadService.class);
//        this.marketProductWriteService = (MarketProductWriteService)this.createProxyInstanceByClass(MarketProductWriteService.class);
//    }
//
//    public List<MarketProductDO> getAllMarketProductDOs(final long siteId) {
//        return (List)this.execute(new ServiceClientInvoker<List<MarketProductDO>>() {
//            public ResultDO<List<MarketProductDO>> invoke() {
//                return PlanClient.this.marketProductReadService.getAllMarketProductDOs(PlanClient.this.appInfo, siteId);
//            }
//        });
//    }
//}