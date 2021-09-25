# MailSender
Simple Application to send gmails directly from app 
Just enter the email destination you want to send mail and it will send it a mail with test subject and body

In the main class change the login and password credentials, just create a new test account in gmail.com to send emails from your test account

If it will give you 535-5.7.8 Username and Password not accepted error, turn less secures app ON in your account - https://www.google.com/settings/security/lesssecureapps

# Run
sudo ./run.sh

# Look at the logs
sudo ./look.sh mailsender

# Restart 
sudo./restart.sh

# Push to github
sudo ./push.sh "Your message here"
