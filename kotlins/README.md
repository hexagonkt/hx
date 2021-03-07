
# Kotlin Scripting

To run Kotlin scripts you have to follow these steps:

1. Install [Kotlin] and make the `kotlinc` binary accesible in the path.
2. Define the `KOTLIN_HOME` environment variable pointing to the Kotlin installation.
3. Get the `kotlins` launcher with the following command:
   ```bash
   curl https://raw.githubusercontent.com/hexagonkt/sidekick/master/kotlins/kotlins \
     -o /usr/local/bin/kotlins
   ```
4. Make the `kotlins` launcher executable with: `chmod +x /usr/local/bin/kotlins`

After those steps, you can run Kotlin scripts with: `./script_name.main.kts args` (assuming that
you marked the script as executable: `chmod +x script_name.main.kts`).

[Kotlin]: https://kotlinlang.org

### Set up IntelliJ
To debug Kotlin scripts, an IntelliJ Java module must be created on the script directory.
