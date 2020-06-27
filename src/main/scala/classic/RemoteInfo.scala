package classic

import com.typesafe.config.{Config, ConfigFactory}

sealed case class RemoteInfo(actorSystemName: String, actorName: String, hostName: String, port: Int) {

  @deprecated("akka configuration string for deprecated remote api")
  def config: Config = {
    val configString: String =
      s"""akka {
         |  actor {
         |    provider = "akka.remote.RemoteActorRefProvider"
         |    serialization-bindings {
         |      "classic.Message" = jackson-json
         |    }
         |  }
         |  remote.artery.enabled = false
         |  remote.classic {
         |    enabled-transports = ["akka.remote.classic.netty.tcp"]
         |    netty.tcp {
         |      hostname = $hostName
         |      port = $port
         |    }
         | }
         |}""".stripMargin

    ConfigFactory.parseString(configString)
  }

  def remoteActorSelectionPath: String =
    s"akka.tcp://$actorSystemName@$hostName:$port/user/$actorName"
}

object RemoteOne extends
  RemoteInfo("ActorSystemOne", "ActorOne", "127.0.0.1", 12345)

object RemoteTwo extends
  RemoteInfo("ActorSystemTwo", "ActorTwo", "127.0.0.1", 12346)
