document.getElementById('emailForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    // Collect form data
    const recipientEmail = document.getElementById('recipientEmail').value;
    const subject = document.getElementById('subject').value;
    const message = document.getElementById('message').value;
    const font = document.getElementById('fontSelect').value;
    const fontSize = document.getElementById('fontSizeSelect').value;
    const fontColor = document.getElementById('fontColor').value;
    const file = document.getElementById('attachment').files[0];

    // Format the message with styling
    const formattedMessage = `<div style="font-family:${font}; font-size:${fontSize}; color:${fontColor};">${message}</div>`;

    const responseMessage = document.getElementById('responseMessage');

    // Prepare the email data
    const emailData = {
        to: recipientEmail,
        subject: subject,
        message: formattedMessage
    };

    // If a file is selected, send using send-with-file API (multipart/form-data)
    if (file) {
        const formData = new FormData();

        // Append the request part as a Blob with Content-Type: application/json
        const requestBlob = new Blob([JSON.stringify(emailData)], { type: 'application/json' });
        formData.append('request', requestBlob);

        // Append the file part
        formData.append('file', file);

        try {
            const response = await fetch('/api/v1/email/send-with-file', {
                method: 'POST',
                body: formData, // Automatically handles content-type as multipart/form-data
            });

            const result = await response.json();
            if (result.success) {
                responseMessage.textContent = 'Email sent successfully with attachment!';
                responseMessage.style.color = 'green';
            } else {
                responseMessage.textContent = 'Error sending email with file.';
                responseMessage.style.color = 'red';
            }
        } catch (error) {
            responseMessage.textContent = 'Error sending email with file.';
            responseMessage.style.color = 'red';
        }

    } else {
        // If no file, send using send API (application/json)
        try {
            const response = await fetch('/api/v1/email/send', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(emailData), // Send data as JSON in the body
            });

            const result = await response.json();
            if (result.success) {
                responseMessage.textContent = 'Email sent successfully!';
                responseMessage.style.color = 'green';
            } else {
                responseMessage.textContent = 'Error sending email.';
                responseMessage.style.color = 'red';
            }
        } catch (error) {
            responseMessage.textContent = 'Error sending email.';
            responseMessage.style.color = 'red';
        }
    }
});