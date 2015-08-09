package com.megaflashgames.budgethelper;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.megaflashgames.budgethelper.annotations.ContentView;
import com.megaflashgames.budgethelper.annotations.InjectView;
import com.megaflashgames.budgethelper.ui.ScreenBase;

/**
 * Created by VanyaMihova on 8/6/2015.
 */
@ContentView(R.layout.start_screen)
public class StartScreen extends ScreenBase {

    private boolean isExpanded = false;
    private boolean isLocked = false;


    @InjectView(R.id.generalContainer)
    private LinearLayout generalContainer;
    @InjectView(R.id.generalSign)
    private TextView generalSign;

    @InjectView(R.id.gPlusContainer)
    private LinearLayout gPlusContainer;
    @InjectView(R.id.gPlusSign)
    private TextView gPlusButton;

    @InjectView(R.id.facebookContainer)
    private LinearLayout facebookContainer;
    @InjectView(R.id.facebookSign)
    private TextView facebookButton;

    @InjectView(R.id.helperContainer)
    private LinearLayout helperContainer;
    @InjectView(R.id.helperSignContainer)
    private LinearLayout helperSignContainer;
    @InjectView(R.id.arrowRight)
    private TextView arrowRight;
    @InjectView(R.id.textSignIn)
    private TextView textSignIn;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        enableInjector(true);
        super.onCreate(savedInstanceState);

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

    private void updateParametersHelperContainer(View view) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(helperContainer.getLayoutParams());
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        helperContainer.setLayoutParams(params);
    }

    private TextView getChildTextViewSignFromView(View view) {
        int childCount = ((LinearLayout)view).getChildCount();
        for(int i = 0; i < childCount; i++) {
            View child = ((LinearLayout) view).getChildAt(i);
            if(child instanceof TextView) {
                if (child.getTag() instanceof String) {
                    if (((String) child.getTag()).equalsIgnoreCase(getString(R.string.tagSign))) {
                        return (TextView) child;
                    }
                }
            }
        }
        return null;
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
            public void onAnimationStart(Animation animation) { }
            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
                view.setOnClickListener(null);
            }
            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        view.startAnimation(animation);
    }

    private void fadeInAnimation(long startOffset, final View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_fade_in);
        animation.setStartOffset(startOffset);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }
            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.VISIBLE);
                view.setOnClickListener(mOnClickListener);
            }
            @Override
            public void onAnimationRepeat(Animation animation) { }
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
        arrowRight.setVisibility(View.GONE);
        behaviorForViewBeforeCollapse(view);
    }

    private void containerExpandAnimationEnd(View view) {
        isLocked = false;
        arrowRight.setVisibility(View.VISIBLE);
        updateParametersHelperContainer(view);
        behaviorForViewAfterExpand(view);
//        updateHelperSignFromView(view);
    }

    private void containerExpandAnimationStart(View view) {
        isLocked = true;
        behaviorForViewBeforeExpand(view);
//        creatyHelperSignFromView(view);
    }

    private void behaviorForViewBeforeExpand(View view) {
        if(view.getId() == R.id.generalContainer) {
            //
        } else if (view.getId() == R.id.facebookContainer) {
            //
        } else if (view.getId() == R.id.gPlusContainer) {
            //
        }
    }

    private void behaviorForViewAfterExpand(View view) {
        if(view.getId() == R.id.generalContainer) {
            //
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
            //
        } else if (view.getId() == R.id.facebookContainer) {
            textSignIn.setVisibility(View.GONE);
        } else if (view.getId() == R.id.gPlusContainer) {
            textSignIn.setVisibility(View.GONE);
        }
    }


    // ***************************
    // General animations

    private Animation generalButtonAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.button_general_appear);
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
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.button_general_expand);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                containerExpandAnimationStart(generalContainer);
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
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.button_general_collapse);
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
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.button_general_sign_translate_left);
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
        return AnimationUtils.loadAnimation(this, R.anim.button_general_sign_translate_right);
    }


    // ***************************
    // G+ animations

    private Animation gPlusButtonAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.button_gplus_appear);
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
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.button_gplus_expand);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                containerExpandAnimationStart(gPlusContainer);
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
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.button_gplus_collapse);
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
        return AnimationUtils.loadAnimation(this, R.anim.button_facebook_appear);
    }

    private Animation facebookContainerExpandAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.button_facebook_expand);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                containerExpandAnimationStart(facebookContainer);
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
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.button_facebook_collapse);
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
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.button_facebook_sign_translate_left);
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
        return AnimationUtils.loadAnimation(this, R.anim.button_facebook_sign_translate_right);
    }


}
