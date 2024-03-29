package com.${packageName}.sdk;

import com.${packageName}.bean.DO.DemoDo;
import com.${packageName}.bean.bo.DemoBo;
import com.star.commons.support.response.ResponseResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * name：指定FeignClient的名称，如果项目使用了Ribbon，name属性会作为微服务的名称，用于服务发现
 * url: url一般用于调试，可以手动指定@FeignClient调用的地址 比如 http://localhost:8080/
 * decode404:当发生http 404错误时，如果该字段为true，会调用decoder进行解码，否则抛出FeignException
 * configuration: Feign配置类，可以自定义Feign的Encoder、Decoder、LogLevel、Contract
 * fallback: 定义容错的处理类，当调用远程接口失败或超时时，会调用对应接口的容错逻辑，fallback指定的类必须实现@FeignClient标记的接口
 * fallbackFactory: 工厂类，用于生成fallback类示例，通过这个属性我们可以实现每个接口通用的容错逻辑，减少重复的代码
 * path: 定义当前FeignClient的统一前缀
*/
@FeignClient(value = "${serverId}",path = "/demo")
public interface ${className}Client {

    @ApiOperation(value = "add", notes = "新增数据 api")
    @ApiImplicitParam(name = "demoDo", value = "新增对象实体",  required = true, paramType = "body", dataTypeClass = DemoDo.class)
    @PostMapping("/v1/add")
    ResponseResult add(@RequestBody DemoDo demoDo);

    @ApiOperation(value = "update", notes = "修改数据 api")
    @ApiImplicitParam(name = "demoDo", value = "修改对象实体", required = true, paramType = "body", dataTypeClass = DemoDo.class)
    @PostMapping(value = "/v1/update")
    ResponseResult update(@RequestBody DemoDo demoDo);

    @ApiOperation(value="通过id删除数据", notes="通过id删除数据 api")
    @ApiImplicitParam(name = "id",  value = "删除实体的id", required = true, dataType = "Long", paramType="path")
    @DeleteMapping("/v1/delete/{id}")
    ResponseResult delete(@PathVariable("id") Long id);

    @ApiOperation(value="get", notes="通过id查询详情信息 api")
    @ApiImplicitParam(name = "id",  value = "查询实体的id", required = true, paramType="path", dataTypeClass = Long.class)
    @GetMapping("/v1/get/{id}")
    ResponseResult<DemoDo> get(@PathVariable("id") Long id);

    @ApiOperation(value = "list", notes = "通过条件查询列表 api")
    @ApiImplicitParam(name = "demoBo", value = "修改对象实体", required = true, paramType = "body", dataTypeClass = DemoBo.class)
    @GetMapping(value = "/v1/list")
    ResponseResult<List<DemoDo>> list(@RequestBody DemoBo demoBo);

    @ApiOperation(value = "通过条件查询分页数据", notes = "通过条件查询分页数据 api")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "demoBo", value = "查询条件实体", required = true, paramType = "body", dataTypeClass = DemoBo.class),
        @ApiImplicitParam(name = "pageIndex", value = "第几页", required = true, paramType = "query", dataTypeClass = Integer.class),
        @ApiImplicitParam(name = "pageSize", value = "每页多少条", required = true, paramType = "query", dataTypeClass = Integer.class)
    })
    @GetMapping(value = "/v1/listByPage")
    ResponseResult<PageInfo<DemoDo>> listByPage(@RequestBody DemoBo demoBo,
                                                @RequestParam("pageIndex") Integer pageIndex,
                                                @RequestParam("pageSize") Integer pageSize);

}
