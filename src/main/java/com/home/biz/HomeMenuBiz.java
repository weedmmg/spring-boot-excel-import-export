package com.home.biz;

import com.home.base.biz.BaseBiz;
import com.home.base.util.TreeUtil;
import com.home.base.vo.MenuTree;
import com.home.entity.HomeMenu;
import com.home.mapper.HomeMenuMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：chenxf
 * @date ：Created in 2019/4/3 14:02
 * @description：菜单业务处理类
 * @modified By：
 * @version: 1.0$
 */
@Service
public class HomeMenuBiz extends BaseBiz<HomeMenuMapper, HomeMenu> {

    public List<MenuTree> queryMenuTree(String type){
        Example example = new Example(HomeMenu.class);
        Example.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotBlank(type)) {
            if(Integer.valueOf(type)<4){
                criteria.andLessThanOrEqualTo("type",  type );
            }else {
                criteria.andEqualTo("type",  type );
            }
        }
        example.setOrderByClause(" order_num asc");
        List<HomeMenu> menus=selectByExample(example);
        return getMenuTree(menus,0L);
    }

    private List<MenuTree> getMenuTree(List<HomeMenu> menus,long root) {
        List<MenuTree> trees = new ArrayList<MenuTree>();
        MenuTree node = null;
        for (HomeMenu menu : menus) {
            node = new MenuTree();
            BeanUtils.copyProperties(menu, node);
            trees.add(node);
        }
        return TreeUtil.buildByRecursive(trees,root) ;
    }
}
