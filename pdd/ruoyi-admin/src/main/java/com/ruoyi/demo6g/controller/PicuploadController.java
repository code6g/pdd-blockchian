package com.ruoyi.demo6g.controller;


import com.ruoyi.code6g.InputUitl;
import com.ruoyi.code6g.sqlUtil.service.SqlUtilService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 图片上传.控制类
 */

@Controller     //控制类标签注解
//@RequestMapping("/lis/lisryxx")
@RequestMapping("")    //
public class PicuploadController extends BaseController {


    // @Autowired
    //private SpxxMapper spxxMapper;          //商品信息Mapper

    @Autowired
    private SqlUtilService sqlUtilService;  //任意sql工具，在复杂业务的情况下可以使用；


    /**
     * 导航到图片上传页面
     */
    @GetMapping("/demo6g/picupload")
    public String toPicupload(ModelMap mmap) {
        //SysUser user = ShiroUtils.getSysUser();
        //mmap.put("user", userService.selectUserById(user.getUserId()));
//        return "demo/avatar";

        return "demo6g/picupload/picupload";
    }

    /**
     * 保存图片到硬盘，返回文件路径
     */
    @Log(title = "保存图片到硬盘", businessType = BusinessType.UPDATE)
    @PostMapping("/demo6g/picupload/insert")
    @ResponseBody
    public AjaxResult insert(@RequestParam("picfile") MultipartFile file) {


        try {
            if (!file.isEmpty()) {

                //保存照片到硬盘，返回文件路径
                String sPicfile = FileUploadUtils.upload(Global.getUploadPath(), file);
                // sPicfile= ”profile/upload/2019/12/24/7a14bd86c5b299c61b25b8187633366c.png“

                // sPicfileFull= “D:\\ruoyi\\uploadPath\\upload\\2019\\12\\24\\7a14bd86c5b299c61b25b8187633366c.png”

                //返回成功信息，和图片路径
                return success(sPicfile);

                //currentUser.setAvatar(avatar);
//                if (userService.updateUserInfo(currentUser) > 0)
//                {
//                    ShiroUtils.setSysUser(userService.selectUserById(currentUser.getUserId()));
//                    return success();
//                }
            }
            return error("");
        } catch (Exception e) {
            // log.error("修改头像失败！", e);
            return error(e.getMessage());
        }


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