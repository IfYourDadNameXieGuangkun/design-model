package exception.aye.controller;

import exception.aye.model.dto.CR;
import exception.aye.model.dto.ControllerResultDto;
import exception.aye.service.ITestExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ITestExceptionService exceptionService;

    @RequestMapping("/exception/{name}")
    public CR<?> testException(@PathVariable("name") String name) {
        return ControllerResultDto.create(exceptionService.test01(name));

    }
}
