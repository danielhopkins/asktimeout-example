package test
import org.specs2.mutable._
import play.api.test.FakeApplication
import play.api.test._
import play.api.test.Helpers._

class ApplicationSpec extends SpecificationWithJUnit {

  "Application" should {
    running(FakeApplication()){
      "start" in {
        true must_== true
      }
    }
  }
}