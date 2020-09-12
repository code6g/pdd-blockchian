package com.ruoyi.demo6g.controller;


import com.ruoyi.code6g.sqlUtil.service.SqlUtilService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.web.domain.server.Sys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 文件上传.控制类
 */

@Controller     //控制类标签注解
//@RequestMapping("/lis/lisryxx")
@RequestMapping("")    //
public class FileuploadController extends BaseController {


    // @Autowired
    //private SpxxMapper spxxMapper;          //商品信息Mapper

    @Autowired
    private SqlUtilService sqlUtilService;  //任意sql工具，在复杂业务的情况下可以使用；

    /**
     * 导航到文件上传页面
     */
    @GetMapping("/demo6g/fileupload")
    public String toFileupload(ModelMap mmap) {
        //SysUser user = ShiroUtils.getSysUser();
        //mmap.put("user", userService.selectUserById(user.getUserId()));
//        return "demo/avatar";

        return "demo6g/fileupload/fileupload";
    }

    /*
    public ActionResult EditPortrait(int id)
    {
         CommonResult result = new CommonResult();
         try
         {
             var files = Request.Files;
             if (files != null && files.Count > 0)
             {
                UserInfo info = BLLFactory<User>.Instance.FindByID(id);
                if (info != null)
                {
                    var fileData = ReadFileBytes(files[0]);
                    result.Success = BLLFactory<User>.Instance.UpdatePersonImageBytes(UserImageType.个人肖像, id, fileData);
                }
             }
         }
         catch (Exception ex)
         {
            result.ErrorMessage = ex.Message;
         }
         return ToJsonContent(result);
     }
     */

    /**
     * 保存图片到硬盘，返回文件路径
     */
    @Log(title = "文件上传到硬盘", businessType = BusinessType.UPDATE)
    @PostMapping("/demo6g/fileupload/insert")
    @ResponseBody
    public AjaxResult insert(@RequestParam("inputFile") MultipartFile file) {

        System.out.println(file.toString());

        //logger.in();
        try {
            String sPathfile = FileUploadUtils.upload(Global.getUploadPath(), file);
            System.out.println(sPathfile);
            return success(file.getOriginalFilename() +"|"+ sPathfile+";");
        }catch (Exception e2){
            return error(e2.toString());

        }

        //return success("pk");
        /*

        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = multipartRequest.getFiles("inputFile");

        try {
            for (MultipartFile file : files) {

                if (!file.isEmpty()) {

                    //保存文件到硬盘，返回文件路径
                    String sPathfile = FileUploadUtils.upload(Global.getUploadPath(), file);
                    // sPathfile= ”profile/upload/2019/12/24/7a14bd86c5b299c61b25b8187633366c.png“

                    // sPathfileFull= “D:\\ruoyi\\uploadPath\\upload\\2019\\12\\24\\7a14bd86c5b299c61b25b8187633366c.png”

            //返回成功信息，和文件路径


            //currentUser.setAvatar(avatar);
            //                if (userService.updateUserInfo(currentUser) > 0)
            //                {
            //                    ShiroUtils.setSysUser(userService.selectUserById(currentUser.getUserId()));
            //                    return success();
            //                }
        }
    }
            return success("ok");

        } catch (Exception e) {
            // log.error("修改头像失败！", e);
            return error(e.getMessage());
        }
          */

        /*
        SysUser currentUser = ShiroUtils.getSysUser();
        try
        {
            if (!file.isEmpty())
            {
                String avatar = FileUploadUtils.upload(Global.getAvatarPath(), file);
                currentUser.setAvatar(avatar);
                if (userService.updateUserInfo(currentUser) > 0)
                {
                    ShiroUtils.setSysUser(userService.selectUserById(currentUser.getUserId()));
                    return success();
                }
            }
            return error();
        }
        catch (Exception e)
        {
            log.error("修改头像失败！", e);
            return error(e.getMessage());
        }
        */
    }

}