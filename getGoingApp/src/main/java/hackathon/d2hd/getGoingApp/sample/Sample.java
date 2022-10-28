package hackathon.d2hd.getGoingApp.sample;

import hackathon.d2hd.getGoingApp.dataModel.Tweet;

public class Sample {
    private Tweet tweet1 = new Tweet(
            "https://t.co/zATocumgqw",
            "BTS",
            "Mami ♡아포방포♡",
            "1452489303225819138",
            "台風に備えて買い出しに行ったらやっと出会えた\uD83D\uDC9C\\n\\n#BTS \\n#TinyTANジョージア \\n#愛するヤンコチ https://t.co/zATocumgqw",
            1L,
            0L,
            0L,
            "2022-09-19 15:54:39 +0900",
            "[{\\\"content\\\":\\\"This store had mostly BTS stuff!\\\",\\\"score\\\":0.0},{\\\"content\\\":\\\"Plus shopping @ MAC - Angelina’s Vlog https://t.co/MHlPhIt2M1 via @YouTube\\\",\\\"score\\\":0.30000001192092896}]"
    );

    private Tweet tweet2 = new Tweet(
            "https://twitter.com/shootmesoftly/status/1571754624859918336",
            "BTS",
            "\uD83C\uDF38 Angelina Grace⁷ \uD83C\uDF38",
            "23654314",
            "This store had mostly BTS stuff! Plus shopping @ MAC - Angelina’s Vlog https://t.co/MHlPhIt2M1 via @YouTube",
            2L,
            1L,
            0L,
            "2022-09-19 15:54:39 +0900",
            "[{\\\"content\\\":\\\"This store had mostly BTS stuff!\\\",\\\"score\\\":0.0},{\\\"content\\\":\\\"Plus shopping @ MAC - Angelina’s Vlog https://t.co/MHlPhIt2M1 via @YouTube\\\",\\\"score\\\":0.30000001192092896}]"
    );

    private Tweet tweet3 = new Tweet(
            "https://twitter.com/shootmesoftly/status/1571754622150414336",
            "BTS",
            "きみちん",
            "1264936886050512896",
            "#JIMIN #ジミン #BTSJIMIN\\nJiminaaaaa\uD83D\uDE0A\uD83D\uDE0A\uD83D\uDE0A\uD83D\uDC9C\uD83D\uDC9C\uD83D\uDC9C\\nJIMIN JIMIN\uD83D\uDC9C\uD83D\uDC9C\uD83D\uDC9C\\n#WeLoveYouJimin\\nFOREVER WITH JIMIN\\n#WeMissYouJimin\\n\\n【音韓】ジミン（BTS（防弾少年団））に投票完了！\uD83D\uDDF3️\\n人気投票はこちらから↓\\n https://t.co/If256kOweO\\n #ジミン #ジミナ #チムチム #パク・ジミン #BTS #防弾少年団",
            0L,
            0L,
            0L,
            "2022-09-19 15:54:38 +0900",
            "[{\\\"content\\\":\\\"#JIMIN #ジミン #BTSJIMIN\\\\nJiminaaaaa\uD83D\uDE0A\uD83D\uDE0A\uD83D\uDE0A\uD83D\uDC9C\uD83D\uDC9C\uD83D\uDC9C\\\\nJIMIN JIMIN\uD83D\uDC9C\uD83D\uDC9C\uD83D\uDC9C\\\\n#WeLoveYouJimin\\\\nFOREVER WITH JIMIN\\\\n#WeMissYouJimin\\\",\\\"score\\\":0.30000001192092896},{\\\"content\\\":\\\"【音韓】ジミン（BTS（防弾少年団））に投票完了！\\\",\\\"score\\\":0.30000001192092896},{\\\"content\\\":\\\"\uD83D\uDDF3️\\\\n人気投票はこちらから↓\\\\n https://t.co/If256kOweO\\\\n #ジミン #ジミナ #チムチム #パク・ジミン #BTS #防弾少年団\\\",\\\"score\\\":0.0}]"
    );

    public Tweet getTweet1() {
        return tweet1;
    }

    public Tweet getTweet2() {
        return tweet2;
    }

    public Tweet getTweet3() {
        return tweet3;
    }
}
