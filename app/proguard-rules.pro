# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# -------------------------------------------------------------#
# BLOQUE LOGS Y PRINT'S
# -------------------------------------------------------------#
-assumenosideeffects class android.util.Log {
    public static *** v(...);
    public static *** d(...);
    public static *** i(...);
    public static *** w(...);
    public static *** e(...);
    public static *** println(...);
}
-dontwarn android.util.Log

# -------------------------------------------------------------#
# BLOQUE MATERIAL ICONS EXTENDED
# -------------------------------------------------------------#
-keep class androidx.compose.material.icons.** { *; }
-dontwarn androidx.compose.material.icons.**


# -------------------------------------------------------------#
# BLOQUE ROOM
# -------------------------------------------------------------#
-keepclassmembers class * {
    @androidx.room.* <methods>;
    @androidx.room.* <fields>;
}
-keep class androidx.room.RoomDatabase { *; }
-keep class * extends androidx.room.RoomDatabase
-dontwarn androidx.room.paging.**


# -------------------------------------------------------------#
# BLOQUE JETPACK COMPOSE
# -------------------------------------------------------------#
-keep class androidx.compose.runtime.** { *; }
-keep class androidx.compose.ui.** { *; }
-keep class androidx.compose.material3.** { *; }
-dontwarn androidx.compose.**

# Compiler generated code
-keepclassmembers class * { @androidx.compose.runtime.Composable *; }

-keep class androidx.navigation.** { *; }
-dontwarn androidx.navigation.**

-keepclassmembers class androidx.navigation.NavBackStackEntry { *; }
-keep class androidx.navigation.NavController { *; }


# -------------------------------------------------------------#
# BLOQUE VIEW MODEL
# -------------------------------------------------------------#
-keep class androidx.lifecycle.ViewModel
-keep class * extends androidx.lifecycle.ViewModel

-keepclassmembers class * extends androidx.lifecycle.ViewModel { <init>(...); }


# -------------------------------------------------------------#
# BLOQUE LIVEDATA Y STATEFLOW
# -------------------------------------------------------------#
-dontwarn kotlinx.coroutines.**
-dontwarn kotlinx.coroutines.flow.**
-dontwarn kotlinx.coroutines.flow.internal.**

-keep class androidx.lifecycle.** { *; }
-dontwarn androidx.lifecycle.**


# -------------------------------------------------------------#
# BLOQUE ATRIBUTOS
# -------------------------------------------------------------#
# Esto conserva solo los números de línea (útil para debug)
# sin revelar nombres de archivos fuente.
-keepattributes LineNumberTable

# Necesario para reflexión con Generics.
-keepattributes Signature

# Imprescindible si uso anotaciones en tiempo de
# ejecución (como @Serializable, @Inject, etc.).
-keepattributes RuntimeVisibleAnnotations

# Mantiene relación entre clases internas y
# externas (puede ser necesaria en reflection).
-keepattributes InnerClasses

# Necesaria si alguna anotación tiene valores por defecto.
-keepattributes AnnotationDefault

# Preserva el método donde fue declarada una clase interna anónima.
-keepattributes EnclosingMethod

# Kotlin Metadata
-keepattributes KotlinMetadata


# -------------------------------------------------------------#
# BLOQUE CLASES: MODELS O ENTITIES
# -------------------------------------------------------------#
-keep class com.zhei.search_arena.core.domain.models.** { *; }
-keep class com.zhei.search_arena.core.data.models.** { *; }


# -------------------------------------------------------------#
# BLOQUE FIREBASE
# -------------------------------------------------------------#
#-keepclassmembers class * {
#    @com.google.firebase.firestore.PropertyName <fields>;
#}

-keep class com.google.firebase.firestore.** { *; }
-dontwarn com.google.firebase.**


# -------------------------------------------------------------#
# BLOQUE GOOGLE AUTH (One Tap + Firebase + await)
# -------------------------------------------------------------#
# Mantener clases de tasks y await de Google Play Services
#-keep class com.google.android.gms.tasks.** { *; }
#-dontwarn com.google.android.gms.tasks.**
#
## Mantener extension de coroutines sobre Task.await()
#-keep class kotlinx.coroutines.tasks.TasksKt { *; }
#-dontwarn kotlinx.coroutines.tasks.TasksKt
#
## Mantener todo lo de Google Identity (OneTap)
#-keep class com.google.android.gms.auth.api.identity.** { *; }
#-dontwarn com.google.android.gms.auth.api.identity.**
#
## Mantener todo lo de FirebaseAuth + GoogleAuthProvider
#-keep class com.google.firebase.auth.** { *; }
#-dontwarn com.google.firebase.auth.**
#
## Proteger One Tap API (SignInClient y Credential)
#-keep class com.google.android.gms.auth.api.identity.SignInClient { *; }
#-keep class com.google.android.gms.auth.api.identity.SignInCredential { *; }
#-keep class com.google.android.gms.auth.api.identity.** { *; }
#-dontwarn com.google.android.gms.auth.api.identity.**

# Clase donde se encuentran los auths
#-keep class com.zhei.comifarm.framework.auth.** { *; }

# modelos de usuario custom están en otro paquete mételos aquí:
# -keep class com.zhei.comifarm.data.model.** { *; }


# -------------------------------------------------------------#
# BLOQUE ANALITICS ( SI USO )
# -------------------------------------------------------------#
-keep class com.google.firebase.analytics.** { *; }


# -------------------------------------------------------------#
# BLOQUE KOTLIN SERIALIZATION
# -------------------------------------------------------------#
# 1. Mantener clases con @Serializable
-keepclasseswithmembers class ** {
    @kotlinx.serialization.Serializable <fields>;
}

# 2. Mantener los métodos serializer() generados
-keepclassmembers class ** {
    public static kotlinx.serialization.KSerializer serializer(...);
}
# 3. Mantener kotlinx
-keep class kotlinx.serialization.** { *; }
-dontwarn kotlinx.serialization.**


# -------------------------------------------------------------#
# BLOQUE HILT Y DAGGER
# -------------------------------------------------------------#
-keep class dagger.** { *; }
-dontwarn dagger.**

-keep class javax.inject.** { *; }
-dontwarn javax.inject.**


# -------------------------------------------------------------#
# BLOQUE DEBUG BUILDS
# -------------------------------------------------------------#
-assumenosideeffects class androidx.compose.ui.tooling.preview.Preview {
    <methods>;
}


# -------------------------------------------------------------#
# MANTENER TEMAS Y COLORES PROPIOS
# -------------------------------------------------------------#
-keep class com.zhei.comifarm.ui.theme.** { *; }