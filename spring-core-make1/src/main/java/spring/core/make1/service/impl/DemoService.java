package spring.core.make1.service.impl;

import spring.core.make1.annotation.GPService;
import spring.core.make1.service.IDemoService;

@GPService
public class DemoService implements IDemoService {
    @Override
    public String getName(String name) {
        return "my name is " + name;
    }
}
