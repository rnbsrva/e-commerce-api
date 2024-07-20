<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shop Request Rejected</title>
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
            color: #f44336;
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
    <h1>Shop Request Rejected</h1>
    <p>Dear ${shopRequest.user.name},</p>
    <p>We regret to inform you that your shop request has been rejected. Below are the details of your request:</p>
    <div class="details">
        <p><strong>Request ID:</strong> ${shopRequest.id}</p>
        <p><strong>Shop Name:</strong> ${shopRequest.name}</p>
        <p><strong>Submission Date:</strong> ${shopRequest.createdDate}</p>
        <p><strong>Rejected Date:</strong> ${shopRequest.reviewedAt}</p>
        <p><strong>Rejection Reason:</strong> ${shopRequest.rejectedReason}</p>
    </div>
    <p>If you have any questions or need further clarification, please contact us.</p>
    <p>We apologize for any inconvenience this may cause.</p>
    <p>Best regards,<br>Your Company</p>
</div>
</body>
</html>
