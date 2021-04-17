package  com.murali.byjus.servicedata.model

data class News (

	val status : String,
	val totalResults : Int,
	val articles : List<Articles>
)