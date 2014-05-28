git checkout projectFiles && a=`find . -iname nbproject -or -iname build.xml` && git checkout war && git checkout projectFiles -- $a && git reset HEAD
