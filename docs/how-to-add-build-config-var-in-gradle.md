```md

// build.gradle.kts

android {
   ...
    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
       ...
        //      API_URL is a property form     gradle.properties
        //        # CUSTOM PROPERTY ADDED
        //        API_URL=MOOOO

        val baseUrl = project.findProperty("API_URL") as? String ?: "https://default.example.com"

        buildConfigField("String", "BASE_URL", "\"${baseUrl}\"")
        resValue("string", "base_url", baseUrl)
        ...
    }
```