package  com.murali.byjus.servicedata.model

import com.google.gson.annotations.SerializedName

data class News (

	@SerializedName("status") val status : String,
	@SerializedName("totalResults") val totalResults : Int,
	@SerializedName("articles") val articles : List<Articles>
)