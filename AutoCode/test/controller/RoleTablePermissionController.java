package controller;

import base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.RoleTablePermission;

import java.util.Map;

@Controller
@RequestMapping("/api/roletablepermission")
public class RoleTablePermissionController extends BaseController
{

    /**
     * 产品线添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    @ResponseBody
    public Map add(@RequestBody RoleTablePermission roleTablePermission)
    {
        try
        {
            return roleTablePermissionService.add(roleTablePermission);
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
            roleTablePermissionService.delete(ids, roleTablePermissionMapper);
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
    public Map update(@RequestBody RoleTablePermission roleTablePermission)
    {
        try
        {
            roleTablePermissionService.update(roleTablePermission);
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
    public Map search(@RequestBody RoleTablePermission roleTablePermission)
    {
        try
        {
            return roleTablePermissionService.search(roleTablePermission);
        } catch (Exception e)
        {
            return error(e.getMessage());
        }
    }
}
