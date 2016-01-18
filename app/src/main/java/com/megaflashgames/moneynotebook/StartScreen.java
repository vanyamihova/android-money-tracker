package com.megaflashgames.moneynotebook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.megaflashgames.moneynotebook.ui.ScreenDashboard;
import com.megaflashgames.moneynotebook.util.enums.LoginStep;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by VanyaMihova on 8/6/2015.
 */
public class StartScreen extends AppCompatActivity {

    private boolean isExpanded = false;
    private boolean isLocked = false;
    private boolean isReadyToSignIn = false;
    private LoginStep loginStep = LoginStep.USERNAME;
    private int progressStatus = 0;
    private String[] signInData = new String[2];


    @Bind(R.id.generalContainer)
    LinearLayout generalContainer;
    @Bind(R.id.gPlusContainer)
    LinearLayout gPlusContainer;
    @Bind(R.id.facebookContainer)
    LinearLayout facebookContainer;

    @Bind(R.id.helperContainer)
    LinearLayout helperContainer;
    @Bind(R.id.helperSignContainer)
    LinearLayout helperSignContainer;
    @Bind(R.id.arrowRight)
    TextView arrowRight;
    @Bind(R.id.textSignIn)
    TextView textSignIn;
    @Bind(R.id.editTextSignInUsername)
    EditText editTextSignInUsername;
    @Bind(R.id.editTextSignInPassword)
    EditText editTextSignInPassword;

    @Bind(R.id.logInContainer)
    RelativeLayout logInContainer;

    @Bind(R.id.loadingContainer)
    RelativeLayout loadingContainer;
    @Bind(R.id.textLoading)
    TextView textLoading;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!isLocked) {
                if (!isExpanded) {
                    extendItem(v.getId());
                } else {
                    collapseItem(v.getId());
                }
                isExpanded = !isExpanded;
            }
        }
    };

    private View.OnClickListener mLoadingOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            if(v.getTag() instanceof View) {

                if(((View)v.getTag()).getId() == R.id.generalContainer) {
                    if(!isReadyToSignIn) {
                        switchEditTexts((View) v.getTag());
                    } else {
                        startLoadView((View) v.getTag());
                    }
                } else if (((View)v.getTag()).getId() == R.id.facebookContainer ||
                        ((View)v.getTag()).getId() == R.id.gPlusContainer) {
                    startLoadView((View) v.getTag());
                }

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);
        ButterKnife.bind(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startAnimations();
            }
        }, 2000);
    }

    private void startAnimations() {
        generalContainer.startAnimation(generalButtonAnimation());
    }

    private void lockView() {
        generalContainer.setOnClickListener(null);
        facebookContainer.setOnClickListener(null);
        gPlusContainer.setOnClickListener(null);
    }

    private void  unlockView() {
        generalContainer.setOnClickListener(mOnClickListener);
        facebookContainer.setOnClickListener(mOnClickListener);
        gPlusContainer.setOnClickListener(mOnClickListener);
    }


    private void extendItem(int expandedItem) {
        if(expandedItem == R.id.generalContainer) {
            copyParametersToHelperContainerFromView(generalContainer);
            helperContainer.startAnimation(generalContainerExpandAnimation());
            generalContainer.startAnimation(generalSignTranslateLeftAnimation());
            fadeOutAnimation(100, gPlusContainer);
            fadeOutAnimation(100, facebookContainer);
        } else if (expandedItem == R.id.facebookContainer) {
            copyParametersToHelperContainerFromView(facebookContainer);
            helperContainer.startAnimation(facebookContainerExpandAnimation());
            facebookContainer.startAnimation(facebookSignTranslateLeftAnimation());
            fadeOutAnimation(0, generalContainer);
            fadeOutAnimation(200, gPlusContainer);
        } else if (expandedItem == R.id.gPlusContainer) {
            copyParametersToHelperContainerFromView(gPlusContainer);
            helperContainer.startAnimation(gPlusContainerExpandAnimation());
            fadeOutAnimation(0, generalContainer);
            fadeOutAnimation(200, facebookContainer);
        }
    }

    private void collapseItem(int expandedItem) {
        if(expandedItem == R.id.generalContainer) {
            isReadyToSignIn = false;
            helperContainer.startAnimation(generalContainerCollapseAnimation());
            generalContainer.startAnimation(generalSignTranslateRightAnimation());
            fadeInAnimation(0, gPlusContainer);
            fadeInAnimation(0, facebookContainer);
        } else if (expandedItem == R.id.facebookContainer) {
            helperContainer.startAnimation(facebookContainerCollapseAnimation());
            facebookContainer.startAnimation(facebookSignTranslateRightAnimation());
            fadeInAnimation(150, generalContainer);
            fadeInAnimation(0, gPlusContainer);
        } else if (expandedItem == R.id.gPlusContainer) {
            helperContainer.startAnimation(gPlusContainerCollapseAnimation());
            fadeInAnimation(150, generalContainer);
            fadeInAnimation(0, facebookContainer);
        }
    }

    private void copyParametersToHelperContainerFromView(View view) {
        RelativeLayout.LayoutParams paramsOld = (RelativeLayout.LayoutParams)view.getLayoutParams();
        helperContainer.setLayoutParams(paramsOld);
        helperContainer.setVisibility(View.VISIBLE);
        view.setBackgroundColor(0);
    }

    private void updateParametersHelperContainer() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(helperContainer.getLayoutParams());
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        helperContainer.setLayoutParams(params);
    }

    private void createHelperSignFromView(final View view) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(view.getLayoutParams());
        helperSignContainer.setVisibility(View.VISIBLE);
        helperSignContainer.setLayoutParams(params);
        helperSignContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setOnClickListener(mOnClickListener);
                view.performClick();
            }
        });
    }

    private void fadeOutAnimation(long startOffset, final View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_fade_out);
        animation.setStartOffset(startOffset);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
                view.setOnClickListener(null);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        view.startAnimation(animation);
    }

    private void fadeInAnimation(long startOffset, final View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_fade_in);
        animation.setStartOffset(startOffset);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.VISIBLE);
                view.setOnClickListener(mOnClickListener);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        view.startAnimation(animation);
    }

    private void containerCollapseAnimationEnd(View view) {
        isLocked = false;
        helperContainer.setVisibility(View.GONE);
        helperSignContainer.setVisibility(View.GONE);
        view.setVisibility(View.VISIBLE);
        view.setBackgroundResource(R.drawable.background_helper);
    }

    private void containerCollapseAnimationStart(View view) {
        isLocked = true;
        arrowRight.setTag(null);
        arrowRight.setOnClickListener(null);
        arrowRight.setVisibility(View.GONE);
        behaviorForViewBeforeCollapse(view);
    }

    private void containerExpandAnimationEnd(View view) {
        isLocked = false;
        arrowRight.setTag(view);
        arrowRight.setOnClickListener(mLoadingOnClickListener);
        arrowRight.setVisibility(View.VISIBLE);
        updateParametersHelperContainer();
        behaviorForViewAfterExpand(view);
    }

    private void containerExpandAnimationStart() {
        isLocked = true;
    }

    private void behaviorForViewAfterExpand(View view) {
        if(view.getId() == R.id.generalContainer) {
            if(loginStep == LoginStep.PASSWORD) {
                if (editTextSignInPassword.getVisibility() == View.GONE)
                    editTextSignInPassword.setVisibility(View.VISIBLE);
            } else {
                editTextSignInUsername.setVisibility(View.VISIBLE);
            }
        } else if (view.getId() == R.id.facebookContainer) {
            textSignIn.setVisibility(View.VISIBLE);
            textSignIn.setText(getString(R.string.textSignInWithFacebook));
        } else if (view.getId() == R.id.gPlusContainer) {
            textSignIn.setVisibility(View.VISIBLE);
            textSignIn.setText(getString(R.string.textSignInWithGoogle));
        }
    }

    private void behaviorForViewBeforeCollapse(View view) {
        if(view.getId() == R.id.generalContainer) {
            editTextSignInUsername.setVisibility(View.GONE);
            editTextSignInPassword.setVisibility(View.GONE);
        } else if (view.getId() == R.id.facebookContainer) {
            textSignIn.setVisibility(View.GONE);
        } else if (view.getId() == R.id.gPlusContainer) {
            textSignIn.setVisibility(View.GONE);
        }
    }

    private void switchEditTexts(View view) {
        if(loginStep == LoginStep.USERNAME) {
            signInData[0] = editTextSignInUsername.getText().toString();
            if(!TextUtils.isEmpty(signInData[0])) {
                loginStep = LoginStep.PASSWORD;
                editTextSignInUsername.startAnimation(edittextDisappearAnimation());
                editTextSignInPassword.startAnimation(edittextApearAnimation());
            } else {
                logInContainer.startAnimation(bounceAnimation());
            }
        } else if(loginStep == LoginStep.PASSWORD) {
            signInData[1] = editTextSignInPassword.getText().toString();
            if(!TextUtils.isEmpty(signInData[1])) {
                startLoadView(view);
            } else {
                logInContainer.startAnimation(bounceAnimation());
            }
        }
    }

    private void startLoadView(View view) {
        fadeOutAnimation(0, logInContainer);
        fadeInAnimation(0, loadingContainer);

        setLoadingTextByView(view);
        updateProgress();
    }

    private void updateProgress() {
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus++;

                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });

                    try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
                }
                Intent intent = new Intent(StartScreen.this, ScreenDashboard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }).start();
    }

    private void setLoadingTextByView(View view) {
        if (view.getId() == R.id.facebookContainer) {
            textLoading.setText(getString(R.string.textLoadingSignInWithFacebook));
        } else if (view.getId() == R.id.gPlusContainer) {
            textLoading.setText(getString(R.string.textLoadingSignInWithGoogle));
        } if (view.getId() == R.id.generalContainer) {
            textLoading.setText(getString(R.string.textLoadingSignInWithGeneral));
        }
    }


    // ***************************
    // logInContainer animations
    private Animation bounceAnimation() {
        return AnimationUtils.loadAnimation(this, R.anim.anim_bounce_horizontal);
    }


    // ***************************
    // EditText animations

    private Animation edittextDisappearAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.login_edittext_disappear);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                editTextSignInUsername.setVisibility(View.GONE);
                editTextSignInUsername.clearAnimation();
            }
            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        return animation;
    }

    private Animation edittextApearAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.login_edittext_appear);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                editTextSignInPassword.setVisibility(View.VISIBLE);
            }
            @Override
            public void onAnimationEnd(Animation animation) {

            }
            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        return animation;
    }


    // ***************************
    // General animations

    private Animation generalButtonAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.login_button_general_appear);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                generalContainer.setVisibility(View.VISIBLE);

                gPlusContainer.startAnimation(gPlusButtonAnimation());
                facebookContainer.startAnimation(facebookAnimation());
            }
            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        return animation;
    }

    private Animation generalContainerExpandAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.login_button_general_expand);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                containerExpandAnimationStart();
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                containerExpandAnimationEnd(generalContainer);
            }
            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        return animation;
    }

    private Animation generalContainerCollapseAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.login_button_general_collapse);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                containerCollapseAnimationStart(generalContainer);
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                containerCollapseAnimationEnd(generalContainer);
            }
            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        return animation;
    }

    private Animation generalSignTranslateLeftAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.login_button_general_sign_translate_left);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }
            @Override
            public void onAnimationEnd(Animation animation) {
                lockView();
                createHelperSignFromView(generalContainer);
            }
            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        return animation;
    }

    private Animation generalSignTranslateRightAnimation() {
        return AnimationUtils.loadAnimation(this, R.anim.login_button_general_sign_translate_right);
    }


    // ***************************
    // G+ animations

    private Animation gPlusButtonAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.login_button_gplus_appear);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                gPlusContainer.setVisibility(View.VISIBLE);
                unlockView();
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        return animation;
    }

    private Animation gPlusContainerExpandAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.login_button_gplus_expand);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                containerExpandAnimationStart();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                containerExpandAnimationEnd(gPlusContainer);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        return animation;
    }

    private Animation gPlusContainerCollapseAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.login_button_gplus_collapse);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                containerCollapseAnimationStart(gPlusContainer);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                containerCollapseAnimationEnd(gPlusContainer);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        return animation;
    }



    // ***************************
    // facebook animations

    private Animation facebookAnimation() {
        return AnimationUtils.loadAnimation(this, R.anim.login_button_facebook_appear);
    }

    private Animation facebookContainerExpandAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.login_button_facebook_expand);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                containerExpandAnimationStart();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                containerExpandAnimationEnd(facebookContainer);
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        return animation;
    }

    private Animation facebookContainerCollapseAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.login_button_facebook_collapse);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                containerCollapseAnimationStart(facebookContainer);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                containerCollapseAnimationEnd(facebookContainer);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        return animation;
    }

    private Animation facebookSignTranslateLeftAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.login_button_facebook_sign_translate_left);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }
            @Override
            public void onAnimationEnd(Animation animation) {
                lockView();
                createHelperSignFromView(facebookContainer);
            }
            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        return animation;
    }

    private Animation facebookSignTranslateRightAnimation() {
        return AnimationUtils.loadAnimation(this, R.anim.login_button_facebook_sign_translate_right);
    }


}
