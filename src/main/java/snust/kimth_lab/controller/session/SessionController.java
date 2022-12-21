//package snust.kimth_lab.controller.session;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//@RestController
//public class SessionController {
//  @GetMapping("/session-info")
//  public ResponseEntity sessionInfo(HttpServletRequest req) {
//    HttpSession session = req.getSession(false);
//    if (session == null) {
//      return ResponseEntity.status();
//    }
//  }
//}
