package com.ersafra.autozap

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_home, "Home")
    object Music : NavigationItem("music", R.drawable.ic_share, "Divida")
    object Movies : NavigationItem("movies", R.drawable.ic_star, "Avalie")
    object About : NavigationItem("about", R.drawable.ic_star, "Sobre")
}