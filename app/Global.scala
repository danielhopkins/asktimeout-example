import play.api.GlobalSettings
import play.api.libs.concurrent.Akka
import play.api.Play.current
import akka.actor.Props
import akka.actor.Actor
import play.api.Application
import akka.dispatch.Await
import akka.util.Timeout
import akka.util.Duration
import akka.pattern.ask
import akka.util.duration._

object Global extends GlobalSettings {

  lazy val echoActor: akka.actor.ActorRef = Akka.system.actorOf(Props(new Actor {
    def receive = {
      case msg ⇒ {
        sender ! msg
      }
    }
  }))

  override def onStart(app: Application) {
    val timeout = Timeout(1.minute)
    val s = Await.result(echoActor.ask("Echo")(timeout), timeout.duration)
    println("Got back a "+s)
  }
}