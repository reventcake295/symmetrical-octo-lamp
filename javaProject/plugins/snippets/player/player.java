//used in the command plugin because thats the only place where sender is used
// check if sender is player
if (!(sender instanceof Player)) {
    CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
    return 0;
}