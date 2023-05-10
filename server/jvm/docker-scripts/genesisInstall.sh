#!/bin/bash
source /home/jbspace/.bashrc
systemctl start postgresql-14
su -c "source /home/jbspace/.bashrc ; genesisInstall" - "jbspace"
echo "genesisInstall done"