_was=0

if  [[ "$*" = *-d* ]]; then
	rm .git/hooks/post-checkout
	rm .git/hooks/post-commit
fi
#set -e
if [ ! -e .git/hooks/post-checkout ]; then
( cat <<"EOF"
#!/bin/sh

echo 'Checking for project files existance.' `date` $0 | tee log.txt

DIR=books
BRANCH=projectfiles
if [ ! -e $DIR/.project ]; then
	echo ".project file does not exist."
	git fetch origin $BRANCH
	git checkout origin/$BRANCH -- $DIR/.project
	git rm --cached $DIR/.project
	echo "downloaded .project file for eclipse project"
fi
if [ ! -e $DIR/.externalToolBuilders ]; then
	echo "Custom builder does not exist."
	git fetch origin $BRANCH
	git checkout origin/$BRANCH -- $DIR/.externalToolBuilders
	git rm -r --cached $DIR/.externalToolBuilders
	echo "Downloaded custom builder. If it is not added by default, you need to add it by yourself."
fi

DIR=Library_client_2014
BRANCH=projectFilesGuiEclipse
if [ ! -e $DIR/.project ]; then
	echo ".project file does not exist."
	git fetch origin $BRANCH
	git checkout origin/$BRANCH -- $DIR/.project
	git rm --cached $DIR/.project
	echo "downloaded .project file for eclipse project"
fi
if [ ! -e $DIR/.externalToolBuilders ]; then
	echo "Custom builder does not exist."
	git fetch origin $BRANCH
	git checkout origin/$BRANCH -- $DIR/.externalToolBuilders
	git rm -r --cached $DIR/.externalToolBuilders
	echo "Downloaded custom builder. If it is not added by default, you need to add it by yourself."
fi


EOF
) > .git/hooks/post-checkout
_was=1
fi

if [ ! -e .git/hooks/post-commit ]; then
( cat <<"EOF"
#!/bin/sh

. `dirname $0`/post-checkout
EOF
) > .git/hooks/post-commit
_was=1
fi

if [ $_was -eq 1 ]; then 
	echo 'At least one file did not exist. Execute hooks.'
	. .git/hooks/post-checkout
else
	echo 'Files existed.'
fi
