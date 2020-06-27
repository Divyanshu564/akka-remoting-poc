package classic

import akka.actor.ActorSystem

trait Run {

  def run(info: RemoteInfo): Unit = {
    val actorSystem: ActorSystem = ActorSystem(info.actorSystemName, info.config)
    val exampleActor = actorSystem.actorOf(ExampleActor.props(info), info.actorName)
    exampleActor ! Start
  }
}
