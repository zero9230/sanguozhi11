package nianyang.mny.controller;

import java.util.List;

import nianyang.mny.infra.baseDO.CharacterDO;
import nianyang.mny.infra.baseDO.CharacterParam;
import nianyang.mny.infra.dao.CharacterDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sikou
 * @date 2021/08/06
 */
@Controller
@RequestMapping(params="charcter.do")
public class CharacterController {

    @Autowired
    private CharacterDAO characterDAO;

    @GetMapping("")
    public CharacterDO getCharacterById(Long id){
        CharacterParam characterParam=new CharacterParam();
        List<CharacterDO> characterDOS = characterDAO.selectByParam(characterParam);
        return characterDOS.get(0);
    }
}
