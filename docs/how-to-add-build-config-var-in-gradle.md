```md

// build.gradle.kts

android {
   ...
    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
       ...
        val baseUrl ="dff"

        buildConfigField("String", "BASE_URL", "\"${baseUrl}\"")
        resValue("string", "base_url", baseUrl)
        ...
    }
```