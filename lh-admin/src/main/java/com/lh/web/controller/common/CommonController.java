package com.lh.web.controller.common;

import com.lh.common.config.HuaweiObsfaceService;
import com.lh.common.config.LhConfig;
import com.lh.common.constant.Constants;
import com.lh.common.core.domain.AjaxResult;
import com.lh.common.utils.StringUtils;
import com.lh.common.utils.file.FileUploadUtils;
import com.lh.common.utils.file.FileUtils;
import com.lh.framework.config.ServerConfig;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用请求处理
 *
 * @author lh
 */
@RestController
public class CommonController
{
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private HuaweiObsfaceService huaweiObsfaceService;

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("common/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!FileUtils.checkAllowDownload(fileName))
            {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = LhConfig.getDownloadPath() + fileName;

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求
     */
    @PostMapping("/common/upload")
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = LhConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            Map map = new HashMap();
            map.put("fileName", fileName);
            map.put("url", url);
            return AjaxResult.success(map);
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 华为云上传请求
     */
    @PostMapping("/huawei/upload")
    public AjaxResult hwUploadFile(MultipartFile file) throws Exception
    {
        String actualName=file.getOriginalFilename();
        String fileUrl= huaweiObsfaceService.upload(file.getInputStream(),actualName);
        AjaxResult ajax = AjaxResult.success();
        StringBuffer stringBuffer = new StringBuffer("https://");
        stringBuffer.append(fileUrl);
        ajax.put("fileName", stringBuffer.toString());
        return  AjaxResult.success(ajax);
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/common/download/resource")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        try
        {
            if (!FileUtils.checkAllowDownload(resource))
            {
                throw new Exception(StringUtils.format("资源文件({})非法，不允许下载。 ", resource));
            }
            // 本地资源路径
            String localPath = LhConfig.getProfile();
            // 数据库资源地址
            String downloadPath = localPath + StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);
            // 下载名称
            String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, downloadName);
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 下载文件 控制器
     *
     * @param path     url地址
     * @param response 响应体
     */
    @ApiOperation(value = "下载文件", notes = "下载文件")
    @ApiImplicitParam(name = "path", value = "url地址", required = true)
    @GetMapping(value = "/common/downloadFile")
    @ResponseBody
    public void downloadFile(@RequestParam String path, HttpServletResponse response) {

        try {
            File tempFile = getFile(path);
            try (FileInputStream fis = new FileInputStream(tempFile)) {
                byte[] buffer = new byte[fis.available()];
                int read = fis.read(buffer);
                // 清空response
                response.reset();
                // 设置response的Header
                response.addHeader("Content-Length", "" + tempFile.length());
                response.addHeader("Content-Disposition", "attachment;filename=" + new String(tempFile.getName().getBytes()));
                OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");
                toClient.write(buffer);
                toClient.flush();
                toClient.close();
            }
        } catch (Exception e) {
            log.error("下载出错-》:{}",e.getMessage());
        }
    }


    /**
     * 创建一个临时文件
     *
     * @param url 远端文件Url
     * @return File
     */
    private File getFile(String url) {
        //对本地文件命名
        String fileName = url.substring(url.lastIndexOf("."), url.length());
        File file = null;

        URL urlfile;
        try {
            // 创建一个临时路径
            file = File.createTempFile("file", fileName);
            log.info("tempFile:->{}",file.getAbsolutePath());
            //下载
            urlfile = new URL(url);

            try (InputStream inStream = urlfile.openStream();
                 OutputStream os = new FileOutputStream(file);) {
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = inStream.read(buffer, 0, 8192)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            }
        } catch (Exception e) {
            log.error("创建临时文件出错：-》{}",e.getMessage());
        }
        return file;
    }
}
