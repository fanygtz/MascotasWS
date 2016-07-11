package mx.fanygtz.mascotasws;


import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

public class ContactoActivity extends AppCompatActivity {
    TextInputLayout lyNombre;
    TextInputLayout lyEmail;
    TextInputLayout lyMsj;
    TextInputLayout lyFrom;
    TextInputLayout lyPassword;
    Button btnEnviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        Toolbar myActionBar = (Toolbar) findViewById(R.id.myActionBar);
        setSupportActionBar(myActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lyNombre = (TextInputLayout) findViewById(R.id.layoutContacto);
        lyEmail = (TextInputLayout) findViewById(R.id.layoutEmail);
        lyMsj = (TextInputLayout) findViewById(R.id.layoutMensaje);
        lyFrom = (TextInputLayout) findViewById(R.id.layoutFrom);
        lyPassword = (TextInputLayout) findViewById(R.id.layoutPassword);
        btnEnviar =(Button) findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }


    private void sendMessage() {
        String[] recipients = { lyEmail.getEditText().getText().toString() };
        SendEmailAsyncTask email = new SendEmailAsyncTask();
        email.activity = this;
        email.m = new Mail(lyFrom.getEditText().getText().toString(), lyPassword.getEditText().getText().toString());
        email.m.set_from(lyFrom.getEditText().getText().toString());
        email.m.setBody(lyMsj.getEditText().getText().toString());
        email.m.set_to(recipients);
        email.m.set_subject(lyNombre.getEditText().getText().toString());
        email.execute();
    }

    public void displayMessage(String message) {
        Snackbar.make(findViewById(R.id.btnEnviar), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}

class SendEmailAsyncTask extends AsyncTask<Void, Void, Boolean> {
    Mail m;
    ContactoActivity activity;

    public SendEmailAsyncTask() {}

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            if (m.send()) {
                activity.displayMessage("Email sent.");
            } else {
                activity.displayMessage("Email failed to send.");
            }

            return true;
        } catch (AuthenticationFailedException e) {
            Log.e(SendEmailAsyncTask.class.getName(), "Bad account details");
            e.printStackTrace();
            activity.displayMessage("Authentication failed.");
            return false;
        } catch (MessagingException e) {
            Log.e(SendEmailAsyncTask.class.getName(), "Email failed");
            e.printStackTrace();
            activity.displayMessage("Email failed to send.");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            activity.displayMessage("Unexpected error occured.");
            return false;
        }
    }
}

