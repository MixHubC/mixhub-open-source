package online.themixhub;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jooby.test.JoobyRule;
import org.jooby.test.MockRouter;
import org.junit.ClassRule;
import org.junit.Test;

import online.themixhub.demo.Demo;

public class AppTest {

  /**
   * One app/server for all the test of this class. If you want to start/stop a new server per test,
   * remove the static modifier and replace the {@link ClassRule} annotation with {@link Rule}.
  @ClassRule
  public static JoobyRule app = new JoobyRule(new Demo());

  @Test
  public void integrationTest() {
    get("/")
        .then()
        .assertThat()
        .body(equalTo("Hello World!"))
        .statusCode(200)
        .contentType("text/html;charset=UTF-8");
  }

  @Test
  public void unitTest() throws Throwable {
    String result = new MockRouter(new Demo())
        .get("/");

    assertEquals("Hello World!", result);
  }
  */

  @Test
  public void firstTest() {
    assertTrue(true); // just seeing if JUnit works
  }

}
