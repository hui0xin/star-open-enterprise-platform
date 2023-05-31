package ${packageController};

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ${packageService}.${fileName}Service;
import ${packageDomain}.${fileDoName};

@RestController
@RequestMapping("/${moduleName}")
@Api(tags = {"${moduleDesc} api操作接口"})
public class ${fileName}Controller {

    @Autowired
    public ${fileName}Service ${fileName?uncap_first}Service;

}