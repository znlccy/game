package com.youda.response;

import com.youda.response.api.ResponseResult;
import javafx.beans.binding.ObjectExpression;
import org.springframework.http.ResponseEntity;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-27
 * @introduce 实现Web端响应的状态码
 */

public class ResponseStatusCode {

    /**
     * 实现添加成功
     *
     * @param bean
     * @return
     */
    public static ResponseEntity postSuccess(Object bean) {
        return ResponseEntity
                .status(201)
                .body(new ResponseResult("201", "添加成功", bean));
    }

    /**
     * 实现操作成功
     *
     * @param bean
     * @return
     */
    public static ResponseEntity putOrGetSuccess(Object bean) {
        return ResponseEntity
                .status(200)
                .body(new ResponseResult("200", "操作成功", bean));
    }

    /*实现操作失败*/
    public static ResponseEntity putOrGetFailed(Object bean) {
        return ResponseEntity
                .status(415)
                .body(new ResponseResult("415", "操作失败", bean));
    }

    /**
     * 实现修改成功
     *
     * @return
     */
    public static ResponseEntity deleteSuccess() {
        return ResponseEntity
                .status(204)
                .body(new ResponseResult("204", "修改成功", "恭喜您修改成功！"));
    }

    /**
     * 实现未通过验证
     *
     * @return
     */
    public static ResponseEntity verifyError() {
        return ResponseEntity
                .status(401)
                .body(new ResponseResult("401", "未通过验证", "对不起，您未通过验证!"));
    }

    /**
     * 实现资源不存在
     *
     * @return
     */
    public static ResponseEntity notFindError() {
        return ResponseEntity
                .status(404)
                .body(new ResponseResult("404", "资源不存在", "抱歉，请求资源不存在!"));
    }

    /**
     * 实现请求非法
     *
     * @return
     */
    public static ResponseEntity illegalError() {
        return ResponseEntity
                .status(407)
                .body(new ResponseResult("407", "请求非法", "对不起，您的请求非法!"));
    }

    /**
     * 实现请求存在冲突
     *
     * @return
     */
    public static ResponseEntity conflictError() {
        return ResponseEntity
                .status(409)
                .body(new ResponseResult("409", "请求存在冲突", "对不起，您的请求存在冲突!"));
    }

    /**
     * 实现服务器错误
     *
     * @return
     */
    public static ResponseEntity cashError() {
        return ResponseEntity
                .status(410)
                .body(new ResponseResult("410", "服务器错误", "抱歉，服务器内部出现错误!"));
    }

    /**
     * 实现空指针错误
     *
     * @return
     */
    public static ResponseEntity nullPointerError() {
        return ResponseEntity
                .status(411)
                .body(new ResponseResult("411", "请求参数不完整", "抱歉，请填写完整!"));
    }

    /**
     * 实现删除失败
     *
     * @return
     */
    public static ResponseEntity deleteFialed() {
        return ResponseEntity
                .status(412)
                .body(new ResponseResult("412", "删除失败", "抱歉，删除失败!"));
    }

    /**
     * 实现密码不一致
     *
     * @return
     */
    public static ResponseEntity passwordsNoMatch() {
        return ResponseEntity
                .status(413)
                .body(new ResponseResult("413", "密码和确认密码不一致", "抱歉,您输入的两次密码不一致"));
    }

    /**
     * 实现用户已存在
     *
     * @return
     */
    public static ResponseEntity userAlreadyExists(Object bean) {
        return ResponseEntity
                .status(414)
                .body(new ResponseResult("414", "用户已存在", bean));
    }

    /*实现上传成功*/
    public static ResponseEntity uploadSuccess() {
        return ResponseEntity
                .status(201)
                .body(new ResponseResult("201", "上传成功","恭喜您，上传成功"));
    }

    /*实现上传失败*/
    public static ResponseEntity uploadFailed() {
        return ResponseEntity
                .status(401)
                .body(new ResponseResult("411", "上传失败", "抱歉，上传失败，请重新上传!"));
    }
}
