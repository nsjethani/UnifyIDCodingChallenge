# UnifyIDCodingChallenge
Coding challenge for unifyID

Fun with Random.org
This challenge is programming language agnostic! Pick whichever language you're most comfortable with. Random.org is a web front-end to an atmospheric noise sensor, which can give us pretty good random numbers. It's the reverse from a noise cancelling filter, since it cancels everything BUT the noise. Weather conditions, solar flares, a full-moon can have little impact on this, since it focuses on getting the purest white noise possible from their hardware sensors. If you too think this is cool, you'd be thrilled to try our challenge:

1. Using the HTTP API for random.org (https://www.random.org/clients/http/) we will ask you to get truly random numbers. Look out for the guidelines, or you may get banned!

2. Using these random numbers create one of the following, to get bonus points:

**_- An RGB bitmap picture of 128x128 pixels. (70 points)_**

- A white noise WAV sound sample of 3 seconds (70 points)

- An RSA key pair (We would discourage you from implementing your own textbook keypair implementation based on exponents and GCD, 100 points)

**I have completed RGB bitmap picture of 128x128 pixels** 

I have generated 128X3 random numbers to generate the image. This can be done via generating 128X128X3 random numbers , aa different pixel(Reg,Green,Blue) value for each pixel, the code of which has been commented in the source code as I had used my daily quota from random.org
