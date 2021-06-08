package com.github.antonnaumov.oauth2;

/**
 * Some world-wide known OAuth2 provider endpoints.
 */
public final class Endpoints {
    private Endpoints() {
    }

    /**
     * Amazon OAuth2 endpoints.
     */
    public static final Endpoint AMAZON = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://www.amazon.com/ap/oa";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://api.amazon.com/auth/o2/token";
        }
    };

    /**
     * Bitbucket OAuth2 endpoints.
     */
    public static final Endpoint BITBUCKET = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://bitbucket.org/site/oauth2/authorize";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://bitbucket.org/site/oauth2/access_token";
        }
    };

    /**
     * CERN OAuth2 endpoints.
     */
    public static final Endpoint CERN = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://oauth.web.cern.ch/OAuth/Authorize";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://oauth.web.cern.ch/OAuth/Token";
        }
    };

    /**
     * Facebook OAuth2 endpoints.
     */
    public static final Endpoint FACEBOOK = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://www.facebook.com/v3.2/dialog/oauth";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://graph.facebook.com/v3.2/oauth/access_token";
        }
    };

    /**
     * FitBit OAuth2 endpoints.
     */
    public static final Endpoint FITBIT = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://www.fitbit.com/oauth2/authorize";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://www.fitbit.com/oauth2/token";
        }
    };

    /**
     * GitHub OAuth2 endpoints.
     */
    public static final Endpoint GITHUB = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://github.com/login/oauth/authorize";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://github.com/login/oauth/access_token";
        }
    };

    /**
     * GitLab OAuth2 endpoints.
     */
    public static final Endpoint GITLAB = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://gitlab.com/oauth/authorize";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://gitlab.com/oauth/token";
        }
    };

    /**
     * Google OAuth2 endpoints.
     */
    public static final Endpoint GOOGLE = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://accounts.google.com/o/oauth2/auth";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://oauth2.googleapis.com/token";
        }
    };

    /**
     * Heroku OAuth2 endpoints.
     */
    public static final Endpoint HEROKU = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://id.heroku.com/oauth/authorize";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://id.heroku.com/oauth/token";
        }
    };

    /**
     * HipChat OAuth2 endpoints.
     */
    public static final Endpoint HIPCHAT = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://www.hipchat.com/users/authorize";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://api.hipchat.com/v2/oauth/token";
        }
    };

    /**
     * Instagram OAuth2 endpoints.
     */
    public static final Endpoint INSTAGRAM = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://api.instagram.com/oauth/authorize";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://api.instagram.com/oauth/access_token";
        }
    };

    /**
     * KaKaO OAuth2 endpoints.
     */
    public static final Endpoint KAKAO = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://kauth.kakao.com/oauth/authorize";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://kauth.kakao.com/oauth/token";
        }
    };

    /**
     * LinkedIN OAuth2 endpoints.
     */
    public static final Endpoint LINKEDIN = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://www.linkedin.com/oauth/v2/authorization";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://www.linkedin.com/oauth/v2/accessToken";
        }
    };

    /**
     * MailChimp OAuth2 endpoints.
     */
    public static final Endpoint MAILCHIMP = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://login.mailchimp.com/oauth2/authorize";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://login.mailchimp.com/oauth2/token";
        }
    };

    /**
     * MediaMath OAuth2 endpoints.
     */
    public static final Endpoint MEDIAMATH = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://api.mediamath.com/oauth2/v1.0/authorize";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://api.mediamath.com/oauth2/v1.0/token";
        }
    };

    /**
     * MediaMath Sandbox OAuth2 endpoints.
     */
    public static final Endpoint MEDIAMATH_SANDBOX = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://t1sandbox.mediamath.com/oauth2/v1.0/authorize";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://t1sandbox.mediamath.com/oauth2/v1.0/token";
        }
    };

    /**
     * Microsoft OAuth2 endpoints.
     */
    public static final Endpoint MICROSOFT = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://login.live.com/oauth20_authorize.srf";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://login.live.com/oauth20_token.srf";
        }
    };

    /**
     * NokiaHealth OAuth2 endpoints.
     */
    public static final Endpoint NOKIAHEALTH = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://account.health.nokia.com/oauth2_user/authorize2";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://account.health.nokia.com/oauth2/token";
        }
    };

    /**
     * PayPal OAuth2 endpoints.
     */
    public static final Endpoint PAYPAL = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://www.paypal.com/webapps/auth/protocol/openidconnect/v1/authorize";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://api.paypal.com/v1/identity/openidconnect/tokenservice";
        }
    };

    /**
     * PayPal Sandbox OAuth2 endpoints.
     */
    public static final Endpoint PAYPAL_SANDBOX = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://www.sandbox.paypal.com/webapps/auth/protocol/openidconnect/v1/authorize";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://api.sandbox.paypal.com/v1/identity/openidconnect/tokenservice";
        }
    };

    /**
     * Slack OAuth2 endpoints.
     */
    public static final Endpoint SLACK = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://slack.com/oauth/authorize";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://slack.com/oauth/authorize";
        }
    };

    /**
     * Spotify OAuth2 endpoints.
     */
    public static final Endpoint SPOTIFY = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://accounts.spotify.com/authorize";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://accounts.spotify.com/api/token";
        }
    };
    /**
     * StackOverflow OAuth2 endpoints.
     */

    public static final Endpoint STACKOWERFLOW = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://stackoverflow.com/oauth";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://stackoverflow.com/oauth/access_token";
        }
    };

    /**
     * Twitch OAuth2 endpoints.
     */
    public static final Endpoint TWITCH = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://id.twitch.tv/oauth2/authorize";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://id.twitch.tv/oauth2/token";
        }
    };

    /**
     * Uber OAuth2 endpoints.
     */
    public static final Endpoint UBER = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://login.uber.com/oauth/v2/authorize";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://login.uber.com/oauth/v2/token";
        }
    };

    /**
     * Yahoo OAuth2 endpoints.
     */
    public static final Endpoint YAHOO = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://api.login.yahoo.com/oauth2/request_auth";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://api.login.yahoo.com/oauth2/get_token";
        }
    };

    /**
     * Zoom OAuth2 endpoints.
     */
    public static final Endpoint ZOOM = new Endpoint() {
        /**
         * {@inheritDoc}
         */
        @Override
        public String authURL() {
            return "https://zoom.us/oauth/authorize";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String tokenURL() {
            return "https://zoom.us/oauth/token";
        }
    };

    /**
     * AzurAD OAuth2 endpoints.
     */
    public static Endpoint AZURAD(final String tenant) {
        final var segment = !"".equals(tenant) && null != tenant ? tenant : "common";
        return new Endpoint() {
            @Override
            public String authURL() {
                return "https://login.microsoftonline.com/" + segment + "/oauth2/v2.0/authorize";
            }

            @Override
            public String tokenURL() {
                return "https://login.microsoftonline.com/" + segment + "/oauth2/v2.0/token";
            }
        };
    }

    /**
     * Composes HipChat custom host OAuth endpoints using the given host.
     *
     * @param host HipChat custom host
     * @return HipChat custom host OAuth endpoints
     */
    public static Endpoint HIPCHAT(final String host) {
        return new Endpoint() {
            @Override
            public String authURL() {
                return "https://" + host + "/users/authorize";
            }

            @Override
            public String tokenURL() {
                return "https://" + host + "/v2/oauth/token";
            }
        };
    }

    /**
     * Composes Amazon WebService Cognito OAuth2 endpoints using the given domain name.
     *
     * @param domain Amazon WebService Cognito domain name.
     * @return Amazon WebService Cognito OAuth2 endpoints.
     */
    public static Endpoint AWSCOGNITO(final String domain) {
        return new Endpoint() {
            @Override
            public String authURL() {
                return domain + "/oauth2/authorize";
            }

            @Override
            public String tokenURL() {
                return domain + "/oauth2/authorize";
            }
        };
    }
}
