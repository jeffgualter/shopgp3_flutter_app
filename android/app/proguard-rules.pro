# Flutter/Dart default keep rules
-keep class io.flutter.** { *; }
-keep class io.flutter.plugins.** { *; }

# Garante que a MainActivity e outras classes não sejam removidas
-keep class com.example.shopgp3_flutter_app.** { *; }

# Evita remoção de classes usadas por reflexão
-keepclassmembers class * {
    public <init>(...);
}

# Regras para manter nomes de métodos e classes úteis
-dontwarn javax.annotation.**
-dontnote javax.annotation.**
