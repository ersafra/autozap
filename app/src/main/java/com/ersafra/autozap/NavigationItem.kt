package com.ersafra.autozap

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_home, "Home")
    object Divida : NavigationItem("music", R.drawable.ic_share, "Divida")
    object Avalie : NavigationItem("movies", R.drawable.ic_star, "Avalie")
    object Sobre : NavigationItem("about", R.drawable.ic_star, "Sobre")
}