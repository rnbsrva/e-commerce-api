<#assign subject = "Email Confirmation">

<html>
<head>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            background-color: #f9f9f9;
        }
        .button {
            display: inline-block;
            padding: 10px 20px;
            margin: 20px 0;
            font-size: 16px;
            color: #ffffff;
            background-color: #007bff;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
        }
        .footer {
            margin-top: 20px;
            font-size: 12px;
            color: #999999;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Email Confirmation</h2>
    <p>Dear ${name},</p>
    <p>Thank you for registering on our platform. Please confirm your email address by clicking the button below:</p>
    <a href="${confirmationLink}" class="button">Confirm Email</a>
    <p>If you did not create an account, no further action is required.</p>
    <p class="footer">Best regards,<br/>The [Your Company] Team</p>
</div>
</body>
</html>
