<#-- shop-request-approved.ftl -->

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shop Request Approved</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 8px;
            background-color: #f9f9f9;
        }
        h1 {
            color: #4CAF50;
        }
        .details {
            margin-top: 20px;
        }
        .details p {
            margin: 5px 0;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Shop Request Approved</h1>
    <p>Dear ${shopRequest.user.name},</p>
    <p>We are pleased to inform you that your shop request has been approved. Below are the details of your request:</p>
    <div class="details">
        <p><strong>Request ID:</strong> ${shopRequest.id}</p>
        <p><strong>Shop Name:</strong> ${shopRequest.name}</p>
        <p><strong>Submission Date:</strong> ${shopRequest.createdDate}</p>
        <p><strong>Approved Date:</strong> ${shopRequest.reviewedAt}</p>
    </div>
    <p>Thank you for your patience. If you have any questions, please contact us.</p>
    <p>Best regards,<br>Your Company</p>
</div>
</body>
</html>
