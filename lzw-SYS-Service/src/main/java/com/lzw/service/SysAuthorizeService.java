package com.lzw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lzw.core.Constants;
import com.lzw.core.base.BaseService;
import com.lzw.mapper.SysAuthorizeMapper;
import com.lzw.mapper.SysRoleMenuMapper;
import com.lzw.mapper.SysUserMenuMapper;
import com.lzw.mapper.SysUserRoleMapper;
import com.lzw.model.SysMenu;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@Service
@CacheConfig(cacheNames = "sysAuthorize")
public class SysAuthorizeService extends BaseService<SysMenu> {




    @Autowired
    private SysAuthorizeMapper sysAuthorizeMapper;


    /**
     * 查询所有权限
     * @return
     */
    @Cacheable(Constants.CACHE_NAMESPACE + "sysAllPermission")
    public List<String> queryAllPermission() {
        return sysAuthorizeMapper.queryAllPermission();
    }

    /**
     * 查询用户的权限
     * @param userId
     * @return
     */
    @Cacheable(Constants.CACHE_NAMESPACE + "sysUserPermission")
    public List<String> queryPermissionByUserId(Long userId) {
        return sysAuthorizeMapper.queryPermissionByUserId(userId);
    }

    /**
     * 查询用户的角色
     * @param userId
     * @return
     */
    @Cacheable(Constants.CACHE_NAMESPACE + "sysUserRole")
    public List<String> queryRoleByUserId(Long userId) {
        return sysAuthorizeMapper.queryRoleByUserId(userId);
    }









    @Autowired
    private SysUserMenuMapper sysUserMenuMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysDicService sysDicService;
	@Autowired
	private SysCacheService sysCacheService;
    






































//    @Transactional
//    @CacheEvict(value = {Constants.CACHE_NAMESPACE + "menuPermission", Constants.CACHE_NAMESPACE + "sysPermission",
//        Constants.CACHE_NAMESPACE + "userPermission"}, allEntries = true)
//    public void updateUserMenu(List<SysUserMenu> sysUserMenus) {
//        Long userId = null;
//        for (SysUserMenu sysUserMenu : sysUserMenus) {
//            if (sysUserMenu.getUserId() != null && "read".equals(sysUserMenu.getPermission())) {
//                userId = sysUserMenu.getUserId();
//            }
//        }
//        if (userId != null) {
//            sysAuthorizeMapper.deleteUserMenu(userId, "read");
//        }
//        for (SysUserMenu sysUserMenu : sysUserMenus) {
//            if (sysUserMenu.getUserId() != null && sysUserMenu.getMenuId() != null
//                && "read".equals(sysUserMenu.getPermission())) {
//                sysUserMenuMapper.insert(sysUserMenu);
//            }
//        }
//    }

//    @Transactional
//    @CacheEvict(value = {Constants.CACHE_NAMESPACE + "menuPermission", Constants.CACHE_NAMESPACE + "sysPermission",
//        Constants.CACHE_NAMESPACE + "userPermission"}, allEntries = true)
//    public void updateUserPermission(List<SysUserMenu> sysUserMenus) {
//        Long userId = null;
//        for (SysUserMenu sysUserMenu : sysUserMenus) {
//            if (sysUserMenu.getUserId() != null) {
//                userId = sysUserMenu.getUserId();
//                break;
//            }
//        }
//        if (userId != null) {
//            Map<String, Object> dicParam = InstanceUtil.newHashMap();
//            dicParam.put("type", "CRUD");
//            List<SysDic> sysDics = sysDicService.queryList(dicParam);
//            for (SysDic sysDic : sysDics) {
//                if (!"read".equals(sysDic.getCode())) {
//                    sysAuthorizeMapper.deleteUserMenu(userId, sysDic.getCode());
//                }
//            }
//        }
//        for (SysUserMenu sysUserMenu : sysUserMenus) {
//            if (sysUserMenu.getUserId() != null && sysUserMenu.getMenuId() != null
//                && !"read".equals(sysUserMenu.getPermission())) {
//                sysUserMenuMapper.insert(sysUserMenu);
//            }
//        }
//    }

//    public List<SysUserRole> getRolesByUserId(Long userId) {
//        SysUserRole sysUserRole = new SysUserRole(userId, null);
//        Wrapper<SysUserRole> wrapper = new EntityWrapper<SysUserRole>(sysUserRole);
//        List<SysUserRole> userRoles = sysUserRoleMapper.selectList(wrapper);
//        return userRoles;
//    }
//
//    public List<Long> getUsersByRoleId(Long roleId) {
//    	if(roleId == null){
//    		return new ArrayList<>();
//    	}
//    	return sysUserRoleMapper.queryUserIdListByRoleId(roleId);
//    }

//    @Transactional
//    @CacheEvict(value = {Constants.CACHE_NAMESPACE + "menuPermission", Constants.CACHE_NAMESPACE + "sysPermission",
//        Constants.CACHE_NAMESPACE + "userPermission", Constants.CACHE_NAMESPACE + "rolePermission"}, allEntries = true)
//    public void updateUserRole(List<SysUserRole> sysUserRoles) {
//        Long userId = null;
//        for (SysUserRole sysUserRole : sysUserRoles) {
//            if (sysUserRole.getUserId() != null) {
//                userId = sysUserRole.getUserId();
//                break;
//            }
//        }
//        if (userId != null) {
//            sysAuthorizeMapper.deleteUserRole(userId);
//        }
//        for (SysUserRole sysUserRole : sysUserRoles) {
//            if (sysUserRole.getUserId() != null && sysUserRole.getRoleId() != null) {
//                sysUserRoleMapper.insert(sysUserRole);
//            }
//        }
//    }

//    public List<String> queryMenuIdsByRoleId(Long roleId) {
//        List<String> resultList = InstanceUtil.newArrayList();
//        List<Long> list = sysRoleMenuMapper.queryMenuIdsByRoleId(roleId);
//        for (Long id : list) {
//            resultList.add(id.toString());
//        }
//        return resultList;
//    }

//    @Transactional
//    @CacheEvict(value = {Constants.CACHE_NAMESPACE + "menuPermission", Constants.CACHE_NAMESPACE + "sysPermission",
//        Constants.CACHE_NAMESPACE + "userPermission", Constants.CACHE_NAMESPACE + "rolePermission"}, allEntries = true)
//    public void updateRoleMenu(List<SysRoleMenu> sysRoleMenus) {
//        Long roleId = null;
//        for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
//            if (sysRoleMenu.getRoleId() != null && "read".equals(sysRoleMenu.getPermission())) {
//                roleId = sysRoleMenu.getRoleId();
//                break;
//            }
//        }
//        if (roleId != null) {
//            sysAuthorizeMapper.deleteRoleMenu(roleId, "read");
//        }
//        for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
//            if (sysRoleMenu.getRoleId() != null && sysRoleMenu.getMenuId() != null
//                && "read".equals(sysRoleMenu.getPermission())) {
//                sysRoleMenuMapper.insert(sysRoleMenu);
//            }
//        }
//    }

//    @Transactional
//    @CacheEvict(value = {Constants.CACHE_NAMESPACE + "menuPermission", Constants.CACHE_NAMESPACE + "sysPermission",
//        Constants.CACHE_NAMESPACE + "userPermission", Constants.CACHE_NAMESPACE + "rolePermission"}, allEntries = true)
//    public void updateRolePermission(List<SysRoleMenu> sysRoleMenus) {
//        Long roleId = null;
//        for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
//            if (sysRoleMenu.getRoleId() != null) {
//                roleId = sysRoleMenu.getRoleId();
//            }
//        }
//        if (roleId != null) {
//            Map<String, Object> dicParam = InstanceUtil.newHashMap();
//            dicParam.put("type", "CRUD");
//            List<SysDic> sysDics = sysDicService.queryList(dicParam);
//            for (SysDic sysDic : sysDics) {
//                if (!"read".equals(sysDic.getCode())) {
//                    sysAuthorizeMapper.deleteRoleMenu(roleId, sysDic.getCode());
//                }
//            }
//        }
//        for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
//            if (sysRoleMenu.getRoleId() != null && sysRoleMenu.getMenuId() != null
//                && !"read".equals(sysRoleMenu.getPermission())) {
//                sysRoleMenuMapper.insert(sysRoleMenu);
//            }
//        }
//    }

//    @Cacheable(value = Constants.CACHE_NAMESPACE + "menuPermission")
//    public List<SysMenu> queryAuthorizeByUserId(Long userId) {
//        List<Long> menuIds = sysAuthorizeMapper.getAuthorize(userId);
//        List<SysMenu> menus = sysMenuService.getList(menuIds);
//        Map<Long, List<SysMenu>> map = InstanceUtil.newHashMap();
//        for (SysMenu sysMenu : menus) {
//            if (map.get(sysMenu.getParentId()) == null) {
//                List<SysMenu> menuBeans = InstanceUtil.newArrayList();
//                map.put(sysMenu.getParentId(), menuBeans);
//            }
//            map.get(sysMenu.getParentId()).add(sysMenu);
//        }
//        List<SysMenu> result = InstanceUtil.newArrayList();
//        for (SysMenu sysMenu : menus) {
//            if (sysMenu.getParentId() == null || sysMenu.getParentId() == 0) {
//                sysMenu.setLeaf(0);
//                sysMenu.setMenuBeans(getChildMenu(map, sysMenu.getId()));
//                result.add(sysMenu);
//            }
//        }
//        return result;
//    }

    // 递归获取子菜单
//    private List<SysMenu> getChildMenu(Map<Long, List<SysMenu>> map, Long id) {
//        List<SysMenu> menus = map.get(id);
//        if (menus != null) {
//            for (SysMenu sysMenu : menus) {
//                sysMenu.setMenuBeans(getChildMenu(map, sysMenu.getId()));
//            }
//        }
//        return menus;
//    }


//    @Cacheable(Constants.CACHE_NAMESPACE + "userPermission")
//    public List<String> queryUserPermission(Long userId) {
//        return sysUserMenuMapper.queryPermission(userId);
//    }

//    @Cacheable(Constants.CACHE_NAMESPACE + "rolePermission")
//    public List<String> queryRolePermission(Long roleId) {
//        return sysRoleMenuMapper.queryPermission(roleId);
//    }
//
//    public List<SysMenu> queryMenusPermission() {
//        return sysAuthorizeMapper.queryMenusPermission();
//    }
//
//    public List<Map<String, Object>> queryUserPermissions(SysUserMenu sysUserMenu) {
//        List<Map<String, Object>> list = sysUserMenuMapper.queryPermissions(sysUserMenu.getUserId());
//        return list;
//    }
//
//    public List<Map<String, Object>> queryRolePermissions(SysRoleMenu sysRoleMenu) {
//        List<Map<String, Object>> list = sysRoleMenuMapper.queryPermissions(sysRoleMenu.getRoleId());
//        return list;
//    }
    
    



}
