package controller;

import base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.RoleFieldPermission;

import java.util.Map;

@Controller
@RequestMapping("/api/rolefieldpermission")
public class RoleFieldPermissionController extends BaseController
{

    /**
     * 产品线添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    @ResponseBody
    public Map add(@RequestBody RoleFieldPermission roleFieldPermission)
    {
        try
        {
            return roleFieldPermissionService.add(roleFieldPermission);
        } catch (Exception e)
        {
            return error(e);
        }
    }

    /**
     * 产品线删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Map delete(@RequestBody Integer[] ids)
    {
        try
        {
            roleFieldPermissionService.delete(ids, roleFieldPermissionMapper);
            return success(DELETE_OK);
        } catch (Exception e)
        {
            return error(e);
        }
    }

    /**
     * 产品线信息修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public Map update(@RequestBody RoleFieldPermission roleFieldPermission)
    {
        try
        {
            roleFieldPermissionService.update(roleFieldPermission);
            return success(UPDATE_OK);
        } catch (Exception e)
        {
            return error(e);
        }
    }

    /**
     * 浏览产品线信息
     */
    @RequestMapping(value = "/search", method = RequestMethod.PUT)
    @ResponseBody
    public Map search(@RequestBody RoleFieldPermission roleFieldPermission)
    {
        try
        {
            return roleFieldPermissionService.search(roleFieldPermission);
        } catch (Exception e)
        {
            return error(e.getMessage());
        }
    }
}
