package controller;

import base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.RoleSysFunctionPermission;

import java.util.Map;

@Controller
@RequestMapping("/api/rolesysfunctionpermission")
public class RoleSysFunctionPermissionController extends BaseController
{

    /**
     * 产品线添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    @ResponseBody
    public Map add(@RequestBody RoleSysFunctionPermission roleSysFunctionPermission)
    {
        try
        {
            return roleSysFunctionPermissionService.add(roleSysFunctionPermission);
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
            roleSysFunctionPermissionService.delete(ids, roleSysFunctionPermissionMapper);
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
    public Map update(@RequestBody RoleSysFunctionPermission roleSysFunctionPermission)
    {
        try
        {
            roleSysFunctionPermissionService.update(roleSysFunctionPermission);
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
    public Map search(@RequestBody RoleSysFunctionPermission roleSysFunctionPermission)
    {
        try
        {
            return roleSysFunctionPermissionService.search(roleSysFunctionPermission);
        } catch (Exception e)
        {
            return error(e.getMessage());
        }
    }
}
