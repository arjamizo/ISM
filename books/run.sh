cd `dirname $0`
which java || zenity --error --text="java can not be found in PATH=$PATH"
java -jar dist/books.jar 2>&1
echo elo | read -d '\t' msg
msg=cze
( which zenity && zenity --info --text=$"cze $msg" ) || echo $"$msg"
cd -
