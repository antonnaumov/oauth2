package com.github.antonnaumov.oauth2;

public final class Endpoints {
    private Endpoints() {
    }

    public static final Endpoint AMAZON = new Endpoint() {
        @Override
        public String authURL() {
            return "https://www.amazon.com/ap/oa";
        }

        @Override
        public String tokenURL() {
            return "https://api.amazon.com/auth/o2/token";
        }
    };

    public static final Endpoint BITBUCKET = new Endpoint() {
        @Override
        public String authURL() {
            return "https://bitbucket.org/site/oauth2/authorize";
        }

        @Override
        public String tokenURL() {
            return "https://bitbucket.org/site/oauth2/access_token";
        }
    };

    public static final Endpoint CERN = new Endpoint() {
        @Override
        public String authURL() {
            return "https://oauth.web.cern.ch/OAuth/Authorize";
        }

        @Override
        public String tokenURL() {
            return "https://oauth.web.cern.ch/OAuth/Token";
        }
    };

    public static final Endpoint FACEBOOK = new Endpoint() {
        @Override
        public String authURL() {
            return "https://www.facebook.com/v3.2/dialog/oauth";
        }

        @Override
        public String tokenURL() {
            return "https://graph.facebook.com/v3.2/oauth/access_token";
        }
    };

    public static final Endpoint FITBIT = new Endpoint() {
        @Override
        public String authURL() {
            return "https://www.fitbit.com/oauth2/authorize";
        }

        @Override
        public String tokenURL() {
            return "https://www.fitbit.com/oauth2/token";
        }
    };

    public static final Endpoint GITHUB = new Endpoint() {
        @Override
        public String authURL() {
            return "https://github.com/login/oauth/authorize";
        }

        @Override
        public String tokenURL() {
            return "https://github.com/login/oauth/access_token";
        }
    };

    public static final Endpoint GITLAB = new Endpoint() {
        @Override
        public String authURL() {
            return "https://gitlab.com/oauth/authorize";
        }

        @Override
        public String tokenURL() {
            return "https://gitlab.com/oauth/token";
        }
    };

    public static final Endpoint GOOGLE = new Endpoint() {
        @Override
        public String authURL() {
            return "https://accounts.google.com/o/oauth2/auth";
        }

        @Override
        public String tokenURL() {
            return "https://oauth2.googleapis.com/token";
        }
    };

    public static final Endpoint HEROKU = new Endpoint() {
        @Override
        public String authURL() {
            return "https://id.heroku.com/oauth/authorize";
        }

        @Override
        public String tokenURL() {
            return "https://id.heroku.com/oauth/token";
        }
    };

    public static final Endpoint HIPCHAT = new Endpoint() {
        @Override
        public String authURL() {
            return "https://www.hipchat.com/users/authorize";
        }

        @Override
        public String tokenURL() {
            return "https://api.hipchat.com/v2/oauth/token";
        }
    };

    public static final Endpoint INSTAGRAM = new Endpoint() {
        @Override
        public String authURL() {
            return "https://api.instagram.com/oauth/authorize";
        }

        @Override
        public String tokenURL() {
            return "https://api.instagram.com/oauth/access_token";
        }
    };

    public static final Endpoint KAKAO = new Endpoint() {
        @Override
        public String authURL() {
            return "https://kauth.kakao.com/oauth/authorize";
        }

        @Override
        public String tokenURL() {
            return "https://kauth.kakao.com/oauth/token";
        }
    };

    public static final Endpoint LINKEDIN = new Endpoint() {
        @Override
        public String authURL() {
            return "https://www.linkedin.com/oauth/v2/authorization";
        }

        @Override
        public String tokenURL() {
            return "https://www.linkedin.com/oauth/v2/accessToken";
        }
    };

    public static final Endpoint MAILCHIMP = new Endpoint() {
        @Override
        public String authURL() {
            return "https://login.mailchimp.com/oauth2/authorize";
        }

        @Override
        public String tokenURL() {
            return "https://login.mailchimp.com/oauth2/token";
        }
    };

    public static final Endpoint MEDIAMATH = new Endpoint() {
        @Override
        public String authURL() {
            return "https://api.mediamath.com/oauth2/v1.0/authorize";
        }

        @Override
        public String tokenURL() {
            return "https://api.mediamath.com/oauth2/v1.0/token";
        }
    };

    public static final Endpoint MEDIAMATH_SANDBOX = new Endpoint() {
        @Override
        public String authURL() {
            return "https://t1sandbox.mediamath.com/oauth2/v1.0/authorize";
        }

        @Override
        public String tokenURL() {
            return "https://t1sandbox.mediamath.com/oauth2/v1.0/token";
        }
    };

    public static final Endpoint MICROSOFT = new Endpoint() {
        @Override
        public String authURL() {
            return "https://login.live.com/oauth20_authorize.srf";
        }

        @Override
        public String tokenURL() {
            return "https://login.live.com/oauth20_token.srf";
        }
    };

    public static final Endpoint NOKIAHEALTH = new Endpoint() {
        @Override
        public String authURL() {
            return "https://account.health.nokia.com/oauth2_user/authorize2";
        }

        @Override
        public String tokenURL() {
            return "https://account.health.nokia.com/oauth2/token";
        }
    };

    public static final Endpoint PAYPAL = new Endpoint() {
        @Override
        public String authURL() {
            return "https://www.paypal.com/webapps/auth/protocol/openidconnect/v1/authorize";
        }

        @Override
        public String tokenURL() {
            return "https://api.paypal.com/v1/identity/openidconnect/tokenservice";
        }
    };

    public static final Endpoint PAYPAL_SANDBOX = new Endpoint() {
        @Override
        public String authURL() {
            return "https://www.sandbox.paypal.com/webapps/auth/protocol/openidconnect/v1/authorize";
        }

        @Override
        public String tokenURL() {
            return "https://api.sandbox.paypal.com/v1/identity/openidconnect/tokenservice";
        }
    };

    public static final Endpoint SLACK = new Endpoint() {
        @Override
        public String authURL() {
            return "https://slack.com/oauth/authorize";
        }

        @Override
        public String tokenURL() {
            return "https://slack.com/oauth/authorize";
        }
    };

    public static final Endpoint SPOTIFY = new Endpoint() {
        @Override
        public String authURL() {
            return "https://accounts.spotify.com/authorize";
        }

        @Override
        public String tokenURL() {
            return "https://accounts.spotify.com/api/token";
        }
    };

    public static final Endpoint STACKOWERFLOW = new Endpoint() {
        @Override
        public String authURL() {
            return "https://stackoverflow.com/oauth";
        }

        @Override
        public String tokenURL() {
            return "https://stackoverflow.com/oauth/access_token";
        }
    };

    public static final Endpoint TWITCH = new Endpoint() {
        @Override
        public String authURL() {
            return "https://id.twitch.tv/oauth2/authorize";
        }

        @Override
        public String tokenURL() {
            return "https://id.twitch.tv/oauth2/token";
        }
    };

    public static final Endpoint UBER = new Endpoint() {
        @Override
        public String authURL() {
            return "https://login.uber.com/oauth/v2/authorize";
        }

        @Override
        public String tokenURL() {
            return "https://login.uber.com/oauth/v2/token";
        }
    };

    public static final Endpoint YAHOO = new Endpoint() {
        @Override
        public String authURL() {
            return "https://api.login.yahoo.com/oauth2/request_auth";
        }

        @Override
        public String tokenURL() {
            return "https://api.login.yahoo.com/oauth2/get_token";
        }
    };

    public static final Endpoint ZOOM = new Endpoint() {
        @Override
        public String authURL() {
            return "https://zoom.us/oauth/authorize";
        }

        @Override
        public String tokenURL() {
            return "https://zoom.us/oauth/token";
        }
    };

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
