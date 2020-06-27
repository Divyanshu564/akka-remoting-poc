package classic

trait Message

case class RemoteMessage(remoteInfo: RemoteInfo) extends Message

case object Start extends Message

case object HelloMessage extends Message
