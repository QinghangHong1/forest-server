# Instructions to build the game for all platforms

 1. Download Unity Engine [here](https://store.unity.com/?_ga=2.255587848.1879503588.1559359244-2090126118.1559359244)
      Choose `For beginners(Personal)`
 2. Open Unity and create an account</li>
 3. Create a new unity 2d project and name the project `Crystalis`
 4. On terminal
  `git clone https://github.com/QinghangHong1/cs48-game`
 5. Change your working directory `cd path/to/Crystalis/Assets`
 6. Delete everything in the Assets folder `rm -r *`
 7. `cp -r path/to/cs48-game/* path/to/Crystalis/Assets`
 8. Open project Crystalis with unity. 
 On top left corner, select `File > Build Settings`. In the project window on the bottom, open folder `Scenes` and drag all scenes (files with unity logo) to the Build Settings window. 
 ***Important: Make sure `DialogueSystem` is at the first position among all scenes in the building settings window.*** Leave the window open.
 9. Click on `Build and run` on the bottom-right corner of the `Build Settings` window you just open. Save the game `Game`.
 10. Double click `Play!` on the bottom-right and the game will start.


      
Server source code is [here](https://github.com/QinghangHong1/crystalis)

Website source code is [here](https://github.com/QinghangHong1/game-website)
