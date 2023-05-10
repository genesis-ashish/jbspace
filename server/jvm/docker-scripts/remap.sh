#!/bin/bash
source /home/jbspace/.bashrc
systemctl start postgresql-14
su -c "source /home/jbspace/.bashrc ; yes | remap --commit" - "jbspace"
su -c "JvmRun global.genesis.environment.scripts.SendTable -t USER -f /home/jbspace/run/site-specific/data/user.csv" - "jbspace"
echo "remap done"
