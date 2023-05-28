
rootProject.name = "infrastructure-project"
include("infra-common")
include("infra-common:common-model")
include("infra-domain")
include("infra-interface")
include("infra-infrastructure")
include("infra-infrastructure:persistence-database")
include("infra-infrastructure:persistence-redis-adapter")
include("infra-common:common-util")