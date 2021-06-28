//import com.design.FactoryMethodBootStrap;
//
//import com.design.dto.AwardReq;
//import com.design.dto.BeanUtils;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = {FactoryMethodBootStrap.class, ApiTest.class})
//public class ApiTest {
//    private MockMvc mockMvc;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @Before
//    public void setup() {
//        // 实例化方式一
////        mockMvc = MockMvcBuilders.standaloneSetup(new Provider2FeignController()).build();
//        // 实例化方式二
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//
//    @Test
//    public void testAwardToUser() throws Exception {
//        AwardReq req01 = AwardReq.builder()
//                .uId("10001")
//                .awardNumber("EGM1023938910232121323432")
//                .awardType(2)//1,2,3
//                .bizId("791098764902132")
//                .build();
//
//        System.out.println(BeanUtils.asJsonString(req01));
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/getNewAward")
//                .content(BeanUtils.asJsonString(req01))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)) //验证响应contentType
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//}
