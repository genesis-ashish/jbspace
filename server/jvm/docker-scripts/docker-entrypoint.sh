#!/bin/bash
systemctl start postgresql-14
systemctl enable sshd.service
systemctl start sshd.service
su -c "startServer" - "jbspace"
echo "Logged as jbspace, starting server"
tail -f /dev/null