_was=0

if  [[ "$*" = *-d* ]]; then
	rm .git/hooks/post-checkout
	rm .git/hooks/post-commit
fi
set -e
if [ ! -e .git/hooks/post-checkout ]; then
( cat <<"EOF"
#!/bin/sh

echo 'Checking for project files existance.' `date` $0 | tee log.txt

if [ ! -e books/.project ]; then
	echo ".project file does not exist."
	git fetch origin projectfiles
	git checkout origin/projectfiles -- ./books/.project
	git rm --cached ./books/.project
	echo "downloaded .project file for eclipse project"
fi
if [ ! -e books/.externalToolBuilders ]; then
	echo "Custom builder does not exist."
	git fetch origin projectfiles
	git checkout origin/projectfiles -- ./books/.externalToolBuilders
	git -r rm --cached ./books/.externalToolBuilders
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
