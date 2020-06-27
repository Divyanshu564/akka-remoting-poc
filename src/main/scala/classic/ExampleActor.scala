package classic

import akka.actor.{Actor, ActorSelection, Props}

object ExampleActor {
  def props(currentInfo: RemoteInfo): Props = Props(new ExampleActor(currentInfo))
}

class ExampleActor(currentInfo: RemoteInfo) extends Actor {

  lazy val remoteActor: ActorSelection = {
    val actorSelectionPath: String = currentInfo match {
      case RemoteOne => RemoteTwo.remoteActorSelectionPath
      case RemoteTwo => RemoteOne.remoteActorSelectionPath
    }
    context.actorSelection(actorSelectionPath)
  }

  override def receive: Receive = {

    case Start =>
      println(s"${self.path}  >>>>>>>>>>>> received start message")
      remoteActor ! HelloMessage

    case HelloMessage =>
      println(s"Hello message from -> ${sender().path}")
      sender() ! classic.RemoteMessage(currentInfo)

    case RemoteMessage(remoteInfo: RemoteInfo) =>
      println(s"Message received from ${sender().path} with message $remoteInfo")
  }
}
