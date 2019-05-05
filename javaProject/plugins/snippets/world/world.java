//get the required data
Player player = (Player) sender;
//make sure the player is in the builderworld
if (!(player.getWorld().getName().equals(world))) {//failure because of incorrect world actions as of result of this differ
    ReturnError.ErrorMessage(ErrorType.wrongCMD, player.getLocale());
    return;
}